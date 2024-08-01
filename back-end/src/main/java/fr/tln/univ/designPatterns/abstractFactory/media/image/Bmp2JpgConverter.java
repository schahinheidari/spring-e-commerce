package fr.tln.univ.designPatterns.abstractFactory.media.image;

import fr.tln.univ.designPatterns.abstractFactory.media.Converter;
import fr.tln.univ.designPatterns.abstractFactory.media.exception.ConvertionException;

import java.io.File;

public class Bmp2JpgConverter extends Converter {
    public Bmp2JpgConverter(byte[] input) {
        super(input);
    }

    public Bmp2JpgConverter(File input) {
        super(input);
    }

    @Override
    public byte[] convert() throws ConvertionException {
        return new byte[0];
    }
}
