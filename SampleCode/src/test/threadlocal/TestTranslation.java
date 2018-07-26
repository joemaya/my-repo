package test.threadlocal;

import com.cognirel.cognitrans.CogniTransTransliterationProvider;

import in.gov.uidai.translit.spi.ITransliterationProvider;
import in.gov.uidai.translit.spi.TransliterationHints;

public class TestTranslation {

	public static void main(String[] args) {
		ITransliterationProvider provider = new CogniTransTransliterationProvider();
		String textToTranslate = "Pankaj";
		String transCode = "04";
		String translatedText = provider.transliterate(textToTranslate, transCode,
							TransliterationHints.TRANSLATE);
		System.out.println(translatedText);
	}
}
