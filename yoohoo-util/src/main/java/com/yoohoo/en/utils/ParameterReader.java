package com.yoohoo.en.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class ParameterReader extends Reader {
	private Reader reader;

	private Writer strWriter = new StringWriter(256);

	public ParameterReader(Reader reader) {
		this.reader = reader;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int size = this.reader.read(cbuf, off, len);
		if (size > 0) {
			strWriter.write(cbuf, off, len);
		}
		return size;
	}

	@Override
	public int read(char[] cbuf) throws IOException {
		int size = this.reader.read(cbuf);
		if (size > 0) {
			strWriter.write(cbuf);
		}
		return size;
	}

	@Override
	public int read() throws IOException {
		int c = this.reader.read();
		if (c >= 0) {
			strWriter.write(c);
		}
		return c;
	}

	@Override
	public void close() throws IOException {
		this.reader.close();

	}

	@Override
	public String toString() {
		return strWriter.toString();
	}

}
