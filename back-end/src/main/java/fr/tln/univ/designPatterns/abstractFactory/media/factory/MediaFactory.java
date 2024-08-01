package fr.tln.univ.designPatterns.abstractFactory.media.factory;

import fr.tln.univ.designPatterns.abstractFactory.media.Converter;

import java.io.File;

public interface MediaFactory {
    Converter createConverter(File file, Converter.CodecTypes codecTypes);
}
