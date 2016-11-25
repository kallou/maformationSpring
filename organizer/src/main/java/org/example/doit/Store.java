package org.example.doit;

public interface Store<T> {
	public void store(T item);
	public T[] findAll();
}
