package Start;


import ActionListeners.CreatingModes;
import Database.DataBase;
import GUI.GUI;
import Model.Amino;


import javax.swing.*;

public class Start {
    private static int currentCandidate = 0;
    private static GUI gui = null;
    private static boolean isCompareMode = false;
    private static DataBase db = new DataBase();

    public static JPanel panel1 = null;
    public static JPanel panel2 = null;
    private static boolean isShiftPressed = false;
    private static boolean isMassDiff = false;
    public static boolean switchToRed = false;
    private static boolean Submitted = false;
    final public static int countInScrollPanel = 10;
    public static double massesPrefix[];
    public static Amino seq[];
    public static int numPrefix = -1;
    final static public int buttonWidth = 60;
    public static boolean isPrefixSelect = false;
    public static boolean isSuffixSelect = false;


    public static int getCurrentCandidate() {
        return currentCandidate;
    }

    public static void minusCur() {
        currentCandidate--;
    }

    public static void plusCur() {
        currentCandidate++;
    }

    public static void setCurrentCandidate(int currentCandidate) {
        Start.currentCandidate = currentCandidate;
    }

    public static boolean isSubmitted() {
        return Submitted;
    }

    public static void setSubmitted(boolean submitted) {
        Submitted = submitted;
    }

    public static boolean isCompareMode() {
        return isCompareMode;
    }

    public static boolean isMassDiff() {
        return isMassDiff;
    }

    public static void setIsMassDiff(boolean isMassDiff) {
        Start.isMassDiff = isMassDiff;
    }

    public static void setIsCompareMode(boolean isCompareMode) {
        Start.isCompareMode = isCompareMode;
    }

    public static DataBase getDb() {
        return db;
    }






    public static boolean isShiftPressed() {
        return isShiftPressed;
    }

    public static void setIsShiftPressed(boolean isShiftPressed) {
        Start.isShiftPressed = isShiftPressed;
    }

    public static GUI getGui() {
        return gui;
    }

    public static void main(String[] args) {
        gui = new GUI("Main");
        gui.addKeyListener(new CreatingModes());
    }
}
