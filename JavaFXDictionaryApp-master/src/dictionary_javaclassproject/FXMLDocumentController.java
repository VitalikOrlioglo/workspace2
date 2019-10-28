/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary_javaclassproject;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author thomassmith
 */


public class FXMLDocumentController implements Initializable {
        
    @FXML 
    private TextArea txtDefinition;
    @FXML 
    private TextField lblWord;
    
    HashMap<String,String> dictionary = new HashMap<>();
    
    @FXML
    void getDefinition(ActionEvent event) {
        
        txtDefinition.setWrapText(true);
        String wordToDefine;
        wordToDefine = lblWord.getText();
        txtDefinition.setText("");
        txtDefinition.appendText(dictionary.get(wordToDefine));
        
        
            
        //System.out.println(dictionary.get(wordToDefine));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    dictionary.put("a","a\n" +
            "ā,ə/Submit\n" +
            "determiner\n" +
            "1.\n" +
            "used when referring to someone or something for the first time in a text or conversation.\n" +
            "\"a man came out of the room\"\n" +
            "2.\n" +
            "used to indicate membership of a class of people or things.\n" +
            "\"he is a lawyer\"");

    dictionary.put("azimuth","az·i·muth  (ăz′ə-məth)\n" +
            "n.\n" +
            "1. The horizontal angular distance from a reference direction, usually the northern point of the horizon, to the point where a vertical circle through a celestial body intersects the horizon, usually measured clockwise. Sometimes the southern point is used as the reference direction, and the measurement is made clockwise through 360°.\n" +
            "2. The horizontal angle of an observer's bearing, measured clockwise from a reference direction such as true north.\n" +
            "3. The horizontal angle of a projectile's motion, measured relative to a reference direction such as true north.");

    dictionary.put("ba","ba1\n" +
            "NOUN\n" +
            "\n" +
            "In ancient Egypt, the supposed soul of a person or god, which survived after death but had to be sustained with offerings of food. It was typically represented as a human-headed bird.\n" +
            "See also ka");

    dictionary.put("bz","Bz\n" +
            "NOUN\n" +
            "\n" +
            "Chemistry \n" +
            "Benzene.\n" +
            "NOUN\n" +
            "\n" +
            "Chemistry \n" +
            "Benzoyl.\n" +
            "Origin\n" +
            "Late 19th century. Symbolic abbreviation for benzene<br>mid 19th century. Symbolic abbreviation for benzoyl.");

    dictionary.put("ca","ca1\n" +
            "\n" +
            "(preceding a date or amount) circa.\n" +
            "‘he was born ca 1400’");

    dictionary.put("car","car\n" +
            "NOUN\n" +
            "\n" +
            "1A road vehicle, typically with four wheels, powered by an internal combustion engine and able to carry a small number of people.\n" +
            "‘we're going by car’");

    dictionary.put("dada","Dada3\n" +
            "NOUN\n" +
            "\n" +
            "An early 20th-century movement in art, literature, music, and film, repudiating and mocking artistic and social conventions and emphasizing the illogical and absurd.");

    dictionary.put("dz","DZ\n" +
            "\n" +
            "1Algeria (international vehicle registration).\n" +
            "2Drop zone.\n" +
            "‘used parachutes were scattered across the DZ’");

    dictionary.put("ea","ea.\n" +
            "\n" +
            "Each.\n" +
            "‘T-shirts for £9.95 ea’");

    dictionary.put("euphemism","eu·phe·mism\n" +
            "ˈyo͞ofəˌmizəm/Submit\n" +
            "noun\n" +
            "noun: euphemism; plural noun: euphemisms\n" +
            "a mild or indirect word or expression substituted for one considered to be too harsh or blunt when referring to something unpleasant or embarrassing.");

    dictionary.put("fa","fa\n" +
            "fä/Submit\n" +
            "nounMUSIC\n" +
            "noun: fa; noun: fah\n" +
            "(in solmization) the fourth note of a major scale.\n" +
            "the note F in the fixed-do system.");

    dictionary.put("faze","faze\n" +
            "fāz/Submit\n" +
            "verbinformal\n" +
            "verb: faze; 3rd person present: fazes; past tense: fazed; past participle: fazed; gerund or present participle: fazing\n" +
            "disturb or disconcert (someone).");

    dictionary.put("ga","Ga\n" +
            "ɡä/Submit\n" +
            "symbol\n" +
            "symbol: Ga\n" +
            "the chemical element gallium.");

    dictionary.put("gz","GZ\n" +
            "abbreviation\n" +
            "Ground Zero.");

    dictionary.put("ha","ha1\n" +
            "hä/Submit\n" +
            "exclamation\n" +
            "exclamation: ha; exclamation: hah\n" +
            "used to express surprise, suspicion, triumph, or some other emotion.");

    dictionary.put("hypothesis","hy·poth·e·sis\n" +
            "hīˈpäTHəsəs/Submit\n" +
            "noun\n" +
            "noun: hypothesis; plural noun: hypotheses\n" +
            "a supposition or proposed explanation made on the basis of limited evidence as a starting point for further investigation.");

    dictionary.put("ia","-ia1\n" +
            "suffix\n" +
            "suffix: -ia\n" +
            "1.\n" +
            "forming nouns adopted unchanged from Latin or Greek (such as mania, militia ), and modern Latin or Greek terms (such as utopia ).\n" +
            "2.\n" +
            "forming names of.\n" +
            "MEDICINE\n" +
            "states and disorders.\n" +
            "\"anemia\"\n" +
            "BOTANYZOOLOGY\n" +
            "genera and higher groups.\n" +
            "\"dahlia\"\n" +
            "3.\n" +
            "forming names of countries.\n" +
            "\"India\"");

    dictionary.put("izzat", "iz·zat\n" +
            "ˈizət/Submit\n" +
            "nounINDIAN\n" +
            "noun: izzat\n" +
            "honor, reputation, or prestige.");

    dictionary.put("jaded","jad·ed\n" +
            "ˈjādəd/Submit\n" +
            "adjective\n" +
            "adjective: jaded\n" +
            "tired, bored, or lacking enthusiasm, typically after having had too much of something.");

    dictionary.put("jazz","jazz\n" +
            "jaz/Submit\n" +
            "noun\n" +
            "noun: jazz\n" +
            "1.\n" +
            "a type of music of black American origin characterized by improvisation, syncopation, and usually a regular or forceful rhythm, emerging at the beginning of the 20th century. Brass and woodwind instruments and piano are particularly associated with jazz, although guitar and occasionally violin are also used; styles include Dixieland, swing, bebop, and free jazz.\n" +
            "informal\n" +
            "enthusiastic or lively talk, especially when considered exaggerated or insincere.\n" +
            "\"all this jazz about how they can't afford it is preposterous\"");

    dictionary.put("ka","ka\n" +
            "[kah] \n" +
            "Examples Word Origin\n" +
            "noun, Egyptian Religion.\n" +
            "1.\n" +
            "a spiritual entity, an aspect of the individual, believed to live within the body during life and to survive it after death.");

    dictionary.put("kz","kz\n" +
            "abbreviation\n" +
            "1.\n" +
            "Kazakhstan");

    dictionary.put("la","la1\n" +
            "[lah] \n" +
            "noun, Music.\n" +
            "1.\n" +
            "the syllable used for the sixth tone of a diatonic scale.\n" +
            "2.\n" +
            "(in the fixed system of solmization) the tone A.\n" +
            "Compare sol-fa");

    dictionary.put("lz","LZ\n" +
            "1.landing zone.");

    dictionary.put("maw", "maw1\n" +
            "[maw] \n" +
            "Examples Word Origin\n" +
            "See more synonyms on Thesaurus.com\n" +
            "noun\n" +
            "1.\n" +
            "the mouth, throat, or gullet of an animal, especially a carnivorous mammal.\n" +
            "2.\n" +
            "the crop or craw of a fowl.\n" +
            "3.\n" +
            "the stomach, especially that of an animal.\n" +
            "4.\n" +
            "a cavernous opening that resembles the open jaws of an animal:\n" +
            "the gaping maw of hell.\n" +
            "5.\n" +
            "the symbolic or theoretical center of a voracious hunger or appetite of any kind:");

    dictionary.put("mz","mz\n" +
            "abbreviation\n" +
            "1.\n" +
            "Mozambique");

    dictionary.put("na","na\n" +
            "[nah, nuh] Chiefly Scot.\n" +
            "adverb\n" +
            "1.\n" +
            "no1.\n" +
            "2.\n" +
            "not; in no way; by no means.");

    dictionary.put("nz","N.Z.or N. Zeal\n" +
            "Examples\n" +
            "1.\n" +
            "New Zealand.");

    dictionary.put("oa", "OA\n" +
            "Examples\n" +
            "1.\n" +
            "office automation.\n" +
            "o/a\n" +
            "1.\n" +
            "on or about.");

    dictionary.put("oz","oz.\n" +
            "1.\n" +
            "ounce; ounces.");

    dictionary.put("pa","Pa\n" +
            "Physics.\n" +
            "1.\n" +
            "pascal; pascals.");

    dictionary.put("paz", "Paz\n" +
            "[pahz; Spanish pahs] \n" +
            "Examples\n" +
            "noun\n" +
            "1.\n" +
            "Octavio  [ok-tey-vee-oh;; Spanish awk-tah-vyaw] (Show IPA), 1914–98, Mexican poet and essayist: Nobel prize 1990.");

    dictionary.put("qa", "Q and Aor Q&A\n" +
            "[kyoo uh n ey, uh nd] \n" +
            "noun, Informal.\n" +
            "1.\n" +
            "an exchange of questions and answers.");

    dictionary.put("quiz","quiz\n" +
            "[kwiz] \n" +
            "Examples Word Origin\n" +
            "See more synonyms on Thesaurus.com\n" +
            "noun, plural quizzes.\n" +
            "1.\n" +
            "an informal test or examination of a student or class.\n" +
            "2.\n" +
            "a questioning.\n" +
            "3.\n" +
            "a practical joke; a hoax.\n" +
            "4.\n" +
            "Chiefly British. ");

    dictionary.put("ra","rā\n" +
            "[rah] \n" +
            "Examples Word Origin\n" +
            "noun\n" +
            "1.\n" +
            "the 10th letter of the Arabic alphabet.");

    dictionary.put("razz","verb (used with object)\n" +
            "1.\n" +
            "to deride; make fun of; tease.");

    dictionary.put("sa", "1.\n" +
            "semiannual.\n" +
            "2.\n" +
            "sex appeal.\n" +
            "3.\n" +
            "subject to approval.");

    dictionary.put("ta","noun\n" +
            "1.\n" +
            "the 16th letter of the Arabic alphabet.");

    dictionary.put("tax","noun\n" +
            "1.\n" +
            "a sum of money demanded by a government for its support or for specific facilities or services, levied upon incomes, property, sales, etc.\n" +
            "2.\n" +
            "a burdensome charge, obligation, duty, or demand.");

    dictionary.put("ua","abbreviation\n" +
            "1.\n" +
            "Ukraine");

    dictionary.put("uz","abbreviation\n" +
            "1.");

    dictionary.put("va","1.\n" +
            "Veterans Administration.\n" +
            "2.\n" +
            "Virginia (approved especially for use with zip code).\n" +
            "3.\n" +
            "Also, va. Electricity. volt-ampere; volt-amperes.");

    dictionary.put("videlicet","adverb, Latin.\n" +
            "1.\n" +
            "that is to say; namely (used especially to introduce examples, details, etc.).");

    dictionary.put("wa","1.\n" +
            "Washington (approved especially for use with zip code).\n" +
            "2.\n" +
            "Banking. withholding agent.");

    dictionary.put("wiz", "noun\n" +
            "1.\n" +
            "wizard (def 3).");

    dictionary.put("xia","[shyah] \n" +
            "noun\n" +
            "1.\n" +
            "a legendary dynasty in China, the traditional dates of which are 2205–1766 b.c.");

    dictionary.put("xyz","Slang.\n" +
            "1.\n" +
            "examine your zipper.");

    dictionary.put("ya","pronoun, Pronunciation Spelling.\n" +
            "1.\n" +
            "you:\n" +
            "Give me a hand, will ya?");

    dictionary.put("yhz","1.\n" +
            "yottahertz.");

    dictionary.put("za","noun, Slang.\n" +
            "1.\n" +
            "pizza");

    dictionary.put("zz","1.\n" +
            "zigzag.");
    }    
    
}
