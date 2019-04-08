/*
 	Created by Eric Mikulin, 2015

	This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/*
	Image to ASCII Art Converter
	Designed for a SWCHS CompSci Club fortnight challenge
	Challenge Components: Speed, Input variety  and Output Aesthetics
*/

package com.willowtreeapps.namegame.jvmcli;

//Import the Packages

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageConverter implements Runnable {
    //Create a map to map between darknesses (int) and characters (ASCII based on darkness). See mapit(); for detail
    static Map<Integer, Character> asciiMap = new HashMap<Integer, Character>();
    //Create the image and link
    static BufferedImage image;
    static URL link;

    public static void displayImage(String url) throws IOException {
        //Create the thread for loading the image
        ImageConverter load = new ImageConverter();    //create another instance of this class
        Thread loadThread = new Thread(load);    //Turn that into a thread

        //Map characters to integers
        mapit();

        link = new URL(url);
        loadThread.start(); //Start the thread

        //Ask for the "Line Skip Coefficient"
        //Basically, this is the amount the x and y values of the coordinate increase each time. Larger = smaller image.
        //Anything above 2 usually doesn't work too well
        int skipC = 2;


        //Test if the thread is still running, then wait for it to finish if it is
        if (loadThread.isAlive()) {
            try {
                loadThread.join();    //Wait for thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Get the length and width of the image, storx`e as integer values
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();


        //This is the conversion loop, goes through every x for each y line
        for (int y = 0; y < imgHeight; y += 2 * skipC) {
            for (int x = 0; x < imgWidth; x += skipC) {
                System.out.print(convert(image.getRGB(x, y)));
            }
            System.out.println();
        }
    }

    //Converts the RGB int value to a char, based on the amount of color in the pixel
    private static char convert(int value) {
        //Grab the three values for each red, green and blue (and alpha)
        int alpha = (value >> 24) & 0xFF;
        int red = (value >> 16) & 0xFF;
        int green = (value >> 8) & 0xFF;
        int blue = (value) & 0xFF;

        //Covert to a unified integer value between 0 and 26.
        //This is done by averaging, then dividing by 10 (RGB values range from 0 to 255)
        int darkness = ((int) ((0.21 * red) + (0.72 * green) + (0.07 * blue) / 3) / 10);

        //If alpha is completely clear, assume white
        if (alpha == 0)
            darkness = 26;

        //Output the respective char, grabbing it from the hashmap
        char chart = asciiMap.get(darkness);
        return chart;
    }

    //This function creates the actual link from the integers and chars
    private static void mapit() {
        //Map an integer darkness value to an ASCII character
        //The value of darkness for the chars I determined from some other random Internet source
        asciiMap.put(0, '@');
        asciiMap.put(1, '@');
        asciiMap.put(2, '@');
        asciiMap.put(3, '@');
        asciiMap.put(4, '@');

        asciiMap.put(5, '@');
        asciiMap.put(6, 'N');
        asciiMap.put(7, '%');
        asciiMap.put(8, 'W');
        asciiMap.put(9, '$');

        asciiMap.put(10, 'D');
        asciiMap.put(11, 'd');
        asciiMap.put(12, 'x');
        asciiMap.put(13, '6');
        asciiMap.put(14, 'E');

        asciiMap.put(15, '5');
        asciiMap.put(16, '{');
        asciiMap.put(17, 's');
        asciiMap.put(18, '?');
        asciiMap.put(19, '!');

        asciiMap.put(20, ';');
        asciiMap.put(21, '"');
        asciiMap.put(22, ':');
        asciiMap.put(23, '_');
        asciiMap.put(24, '\'');

        asciiMap.put(25, '`');
        asciiMap.put(26, ' ');
    }

    //The function run for when you need multi-thread
    public void run() {
        try {
            image = ImageIO.read(link);    //Load the image from the web
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
