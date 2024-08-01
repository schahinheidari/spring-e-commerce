package fr.tln.univ.designPatterns.abstractFactory.media;

import fr.tln.univ.designPatterns.abstractFactory.media.exception.ConvertionException;

import java.io.File;

public abstract class Converter {
    private byte[] input;

    public Converter(byte[] input) {
        this.input = input;
    }

    public Converter(File file){

    }

    public abstract byte[] convert() throws ConvertionException;

    public enum CodecTypes{
        BMP, JPG, PNG,
        MP3, WAV, AAC,
        MP4, AVI, MKV
    }


}

