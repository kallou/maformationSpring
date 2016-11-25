package org.example;

import org.example.model.Event;
import org.example.model.EventSetFieldMapper;
import org.example.repository.EventRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
@EnableBatchProcessing
public class ImportBatchConfiguration {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	public JobBuilderFactory JobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public ItemReader<Event> eventReader(){
		FlatFileItemReader<Event> reader = new FlatFileItemReader<>();
		
		reader.setResource(new ClassPathResource("import.csv"));
		
		DefaultLineMapper<Event> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer tokenizer    = new DelimitedLineTokenizer(); // comma by default
		tokenizer.setNames(new String[]{"description", "beginDateTime", "endDateTime"});
		
		FieldSetMapper<Event> fieldSetMapper = new EventSetFieldMapper();
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		lineMapper.setLineTokenizer(tokenizer);
		reader.setLineMapper(lineMapper);
		
		return reader;
	}
	
	@Bean
	public ItemWriter<Event> eventWriter(){
		RepositoryItemWriter<Event> writer = new RepositoryItemWriter<>();
		writer.setRepository(eventRepository);
		writer.setMethodName("save");
		return writer;		
	}
	
	
	@Bean
	public ItemProcessor<Event, Event> eventProcessor(){
		return new PassThroughItemProcessor<>();
	}

	@Bean
	public Job importJob(){
		return JobBuilderFactory.get("importEvents")
			.incrementer(new RunIdIncrementer())
			.flow(step())
			.end()
			.build();
	}
	

	@Bean
	public Step step(){
		return stepBuilderFactory.get("step")
			.<Event, Event>chunk(10)
			.reader(eventReader())
			.processor(eventProcessor())
			.writer(eventWriter())
			.build();
	}
}
