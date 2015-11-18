package net.as93.tunesender;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TuneValidTest {

    /**
     * Test that isValid() returns false for all invalid tones
     * (where a tone is 1 component of a tune)
     */
    @Test
    public void rejectInvalidTones(){
        String[] rawTunes = new String[]{
                "",     // Should reject empty string
                "2",    // And really short string

                "D24",  // Begins with invalid character
                "5D4",  // Begins with invalid duration
                "7D4",  // Begins with invalid duration
                " D4",  // Begins with invalid character (white space)

                "2H4",  // Includes an invalid note
                "2 4",  // Includes a very invalid note (white space)
                "234",  // Includes an invalid note (number)

                "6C55", // Invalid scientific pitch notation (number)
                "6CD5", // Invalid scientific pitch notation (letter)
                "6C 5", // Invalid scientific pitch notation (wSpace)
                "6CB",  // Invalid scientific pitch notation (no num)
                "6C##", // Invalid scientific pitch notation (Double hash)
                "6C##5",// Invalid scientific pitch notation (Double hash with num)
                "6C",   // Forgot scientific pitch notation
        };

        // Run the tests on each raw tone - test will pass if isValid() is false
        for(String rawTune: rawTunes){
            assertFalse(new Tune(rawTune).isTuneValid());
        }

    }


    /**
     * Test that isValid() returns true for all valid tones
     * (where a tone is 1 component of a tune)
     */
    @Test
    public void acceptValidTones(){
        String[] rawTunes = new String[]{
            "2D4",  // Standard example
            "2d2",  // Lower case letters
            "4C#5", // Includes a hash for pitch
            "2Eb4", // Includes a b for the pitch
            "2EB4", // Includes a uppercase B in pitch
            "6ab9", // Larger number for pitch
            "8bb1", // Smaller number for pitch
        };

        // Run the tests on each raw tone - test will pass if isValid() is true
        for(String rawTune: rawTunes){
            assertTrue(new Tune(rawTune).isTuneValid());
        }
    }


    /**
     * Putting it together, checking that a tune made up of multiple tones still
     * works as expected, and accepts tunes if all tones are valid.
     */
    @Test
    public void acceptValidTunes(){
        assertTrue(new Tune("2D4 4C#5 2EB4 8bb1 2Eb4").isTuneValid());
        assertTrue(new Tune("1a#1 2BB2 3cb8 4D#7 6E4").isTuneValid());
        assertTrue(new Tune("2D4 2D4 4E4 4D4 4G4 8F#4").isTuneValid());
    }


    /**
     * Rejecting tunes if one or more tones are incorrect
     */
    @Test
    public void rejectInvalidTunes(){
        assertFalse(new Tune("2D4 4C#5 2EB4 8bb1 2Eb").isTuneValid());  // missed final pitch
        assertFalse(new Tune("1a#1 5BB2 3cb8 4D#7 6E4").isTuneValid()); // Invalid duration 5
        assertFalse(new Tune("2D4 2D4 4Z4 4D4 4G4 8F#4").isTuneValid()); // Invalid note Z
    }

}