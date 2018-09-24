package Chapter5_8;

public class Main {
    public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        if (x1 >= width || x2 >= width) {
            throw new IllegalArgumentException("fail");
        }

        int heightOffset = width * y;

        int x1ByteOffset = x1 / 8;
        int x2ByteOffset = x2 / 8;

        int x1ByteStart = x1 % 8;
        screen[heightOffset + x1ByteOffset] |= (byte) (~(0xFF >> x1ByteStart));

        for (int i = heightOffset + x1ByteOffset + 1; i < heightOffset + x2ByteOffset; i++) {
            screen[i] = -1;
        }

        int x2ByteStart = x2 % 8;
        screen[heightOffset + x2ByteOffset] |= (byte) (0xFF >> x2ByteStart + 1);
    }
}
