package fr.tln.univ.designPatterns.abstractFactory.media.factory;

public interface MediaConverterAbstractFactory {

    enum MediaFactoryType{
        VIDEO, MUSIC, IMAGE
    }

    static MediaFactory createFactory(MediaFactoryType mediaFactoryType){
        switch (mediaFactoryType){
            case IMAGE -> {
                return new ImageConverterFactory();
            }
            case MUSIC -> {
                return new MusicConverterFactory();
            }
            case VIDEO -> {
                return new VideoConverterFactory();
            }
        }
        throw new IllegalArgumentException("Bad type");
    }
}
