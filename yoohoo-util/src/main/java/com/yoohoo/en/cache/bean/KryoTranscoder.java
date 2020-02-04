package com.yoohoo.en.cache.bean;

import org.apache.log4j.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.transcoders.Transcoder;

public class KryoTranscoder extends SerializingTranscoder implements Transcoder<Object> {

	private static Logger logger = Logger.getLogger(KryoTranscoder.class);
	private Kryo kryo;
	private int initSize;
	private int maxSize;

	public KryoTranscoder(int initSize, int maxSize) {
		this.kryo = new Kryo();
		this.initSize = (initSize * 1024);
		this.maxSize = (maxSize * 1024);
	}

	protected byte[] serialize(Object o) {
		if (o == null) {
			throw new NullPointerException("Can't serialize null!");
		}

		byte[] rv = new byte[0];
		try {
			Output output = new Output(initSize,maxSize);
			kryo.writeClassAndObject(output, o);
			rv = output.toBytes();
			output.close();
		} catch (Exception e) {
			logger.error("object serialize faild. ", e);
		}

		return rv;
	}

	protected Object deserialize(byte[] in) {
		if (null == in || in.length < 1)
			return null;
		Object rv = null;
		try {
			Input input = new Input(in);
			rv = kryo.readClassAndObject(input);
			input.close();
		} catch (Exception e) {
			logger.error("object deserialize faild. ", e);
		}

		return rv;
	}
}