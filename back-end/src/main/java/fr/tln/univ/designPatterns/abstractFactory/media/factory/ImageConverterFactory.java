package fr.tln.univ.designPatterns.abstractFactory.media.factory;

import fr.tln.univ.designPatterns.abstractFactory.media.Converter;
import fr.tln.univ.designPatterns.abstractFactory.media.image.Bmp2JpgConverter;

import java.io.File;

public class ImageConverterFactory implements MediaFactory{

    @Override
    public Converter createConverter(File file, Converter.CodecTypes codecTypes){
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".bmp")){
            switch (codecTypes){
                case JPG -> {
                    return new Bmp2JpgConverter(file);
                }
            }
            throw new IllegalStateException("Converter not found");
        }

        return null;
    }
}
