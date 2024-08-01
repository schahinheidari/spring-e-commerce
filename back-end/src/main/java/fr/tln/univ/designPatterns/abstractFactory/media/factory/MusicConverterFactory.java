package fr.tln.univ.designPatterns.abstractFactory.media.factory;

import fr.tln.univ.designPatterns.abstractFactory.media.Converter;
import fr.tln.univ.designPatterns.abstractFactory.media.image.Bmp2JpgConverter;

import java.io.File;

public class MusicConverterFactory implements MediaFactory{

    @Override
    public Converter createConverter(File file, Converter.CodecTypes codecTypes){
        String fileName = file.getName().toLowerCase();

            throw new IllegalStateException("Converter not found");
        }
    }
