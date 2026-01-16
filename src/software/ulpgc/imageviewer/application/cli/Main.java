package software.ulpgc.imageviewer.application.cli;

import software.ulpgc.imageviewer.application.FileImageStore;
import software.ulpgc.imageviewer.architecture.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Console.create()
                .initDisplay()
                .initCommands()
                .execute();
    }

}
