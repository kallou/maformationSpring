package org.example.doit;

public interface Store<T> {
	public void store(T[] tab);
	public T[] readAll();
}
