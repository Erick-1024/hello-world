//package test.cfca.sadk.testdata;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * just for Test
// * 
// * @author qazhang
// * 
// */
//public final class FileHelper {
//
//    private FileHelper() {
//    }
//
//    static final public void deleteFile(String filename) {
//        try {
//            File file = new File(filename);
//            if (file.exists()) {
//                file.delete();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @param out
//     * @param offset
//     * @param filepath
//     * @return fileLength
//     * @throws IOException
//     */
//    static final public int loadFile(final byte[] out, int offset, String filepath) throws IOException {
//        if (out == null) {
//            throw new IllegalArgumentException("Illegal Argument: out");
//        }
//        if (filepath == null) {
//            throw new IllegalArgumentException("Illegal Argument: filepath");
//        }
//
//        final int starter = offset;
//        FileInputStream crls = null;
//        try {
//            crls = new FileInputStream(filepath);
//            if (out.length < offset + crls.available()) {
//                throw new IllegalArgumentException("Illegal Argument: filepath");
//            }
//
//            byte[] buffer = new byte[1024];
//            int numRead;
//            while ((numRead = crls.read(buffer, 0, buffer.length)) >= 0) {
//                System.arraycopy(buffer, 0, out, offset, numRead);
//                offset += numRead;
//            }
//            return offset - starter;
//        } catch (IOException e) {
//            throw e;
//        } finally {
//            if (crls != null) {
//                try {
//                    crls.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//
//    }
//
//    static final public void write(String filepath, byte[] data) throws IOException {
//        if (filepath == null) {
//            throw new IllegalArgumentException("Illegal Argument: filepath");
//        }
//        if (data == null) {
//            throw new IllegalArgumentException("Illegal Argument: data");
//        }
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(filepath);
//            fos.write(data, 0, data.length);
//            fos.flush();
//        } catch (IOException e) {
//            throw e;
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (Exception e) {
//                }
//            }
//
//        }
//
//    }
//
//    /**
//     * 
//     * @param filepath
//     * @return fileBytes
//     * @throws IOException
//     */
//    static final public byte[] read(String filepath) throws IOException {
//
//        if (filepath == null) {
//            throw new IllegalArgumentException("Illegal Argument: filepath");
//        }
//
//        int offset = 0;
//        FileInputStream crls = null;
//        try {
//            crls = new FileInputStream(filepath);
//            byte[] out = new byte[crls.available()];
//
//            if (out.length < offset + crls.available()) {
//                throw new IllegalArgumentException("Illegal Argument: filepath");
//            }
//
//            byte[] buffer = new byte[1024];
//            int numRead;
//            while ((numRead = crls.read(buffer, 0, buffer.length)) >= 0) {
//                System.arraycopy(buffer, 0, out, offset, numRead);
//                offset += numRead;
//            }
//            return out;
//        } catch (IOException e) {
//            throw e;
//        } finally {
//            if (crls != null) {
//                try {
//                    crls.close();
//                } catch (Exception e) {
//                }
//            }
//
//        }
//
//    }
//
//}
