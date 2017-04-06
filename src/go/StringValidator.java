
package go;

/**
 *
 * @author Alex
 */
public class StringValidator {

    private String input;
    private String opt1;
    private String opt2;
    private boolean correct;

    public StringValidator(String input, String opt1) {
        this.input = input;
        this.opt1 = opt1;
    }

    public StringValidator(String input, String opt1, String opt2) {
        this.input = input;
        this.opt1 = opt1;
        this.opt2 = opt2;
    }

    public boolean validateSingleOpt() {
        if (input.equals(opt1)) {
            correct = true;
            return correct;
        } else {
            correct = false;
            return correct;
        }
    }

    public boolean validateDoubleOpt() {
        if (input.equals(opt1) || input.equals(opt2)) {
            correct = true;
            return correct;
        } else {
            correct = false;
            return correct;

        }
    }
    
    public void setInput(String in) {
        input = in;
    }
    
}
