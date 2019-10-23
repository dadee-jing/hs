package com.hs.platform.station.upload_foshan.socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class FoshanCodecFactory implements ProtocolCodecFactory {

    private ProtocolEncoder encoder;
    private ProtocolDecoder decoder;

    public FoshanCodecFactory() {
        this.encoder = new FoshanEncoder();
        this.decoder = new FoshanDecoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
