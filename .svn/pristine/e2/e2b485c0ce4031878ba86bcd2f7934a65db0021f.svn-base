package de.uniwue.jpp.encoder;

public abstract class AbstractChainableEncoder implements Encoder {

	// Objekte dieser Klasse repräsentieren Encoder,
	// die aneinandergehängt werden können (also FixedEncoder oder RotatingEncoder).
	// Sie besitzt mindestens folgende öffentliche Methoden:
	Encoder delegate;  //  Nachfolger

	public AbstractChainableEncoder(Encoder delegate) throws EncoderCreationException {
		if (delegate==null) throw new EncoderCreationException("");
		this.delegate=delegate;
	}

	public Encoder getDelegate() {
		return delegate; // Liefert den zugewiesenen Nachfolger zurück
	}
}