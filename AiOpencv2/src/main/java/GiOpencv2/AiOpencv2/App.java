package GiOpencv2.AiOpencv2;


import java.io.IOException;
import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;


/**
 * Hello world!
 *
 */
public class App 
{	    
    public static void main( String[] args ) throws IOException
    {
    	//carichiamo la libreria
		System.loadLibrary("opencv_java452");
		//Stampiamo la versione della libreria che stiamo usando
		System.out.println("Welcome to OpenCV " + Core.VERSION);
		
		//questo viene è un test per verificare openCV
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = " + mat.dump());
        
        String template = "C:\\Users\\angelo\\eclipse-workspace\\AiOpencv2\\resources\\da_trovare.JPG";
        
        //altro test che crea una matrice
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("");
        // stampa le proprietà della matrice
        System.out.println("OpenCV Mat: " + m);
        //inserisce gli 1 nella riga 1
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        //inserisce gli 2 nella colonna 2
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        //Stampa la matrice
        System.out.println("OpenCV Mat data:\n" + m.dump());
        
         
        //La matrice viene usata er contenere le immagini
        Mat imageArray;
        //Legge l'immagine contenuta su C:\\Users\\angelo\\eclipse-workspace\\AiOpencv2\\resources\\da_trovare.JPG
        imageArray=Imgcodecs.imread(template);
        //Stampa larghezza ed altezza della matrice nel quale è allocata l'immagine
        System.out.println(imageArray.rows());
        System.out.println(imageArray.cols());
        
        
        //Legge una immagine e fa la copia
        //crea una nuova matrice
        Mat copia=new Mat();
        //Legge l'immagine e l'assegna alla matrice  C:\\Users\\angelo\\eclipse-workspace\\AiOpencv2\\resources\\da_trovare.JPG
        copia=Imgcodecs.imread(template);
        //Disegna il rettangolo contenente il numero 1 da trovare
        Imgproc.rectangle(copia, new Point(10,200), new Point(100,400),new Scalar(0,0,255));
        Imgcodecs.imwrite("C:\\test\\copia.jpg", copia);
        System.out.println("creata immagine di copia sotto c:\\test");
        
        
        System.out.println("Ricerca di una immagine dentro una immagine");
        //Ricerca di una immagine dentro l'immagine
        Mat source=null;
        Mat template1=null;
        //percorso di base
        String filePath="C:\\Users\\angelo\\eclipse-workspace\\AiOpencv2\\resources\\";
        //Carica i file immagine
        source=Imgcodecs.imread(filePath+"calcolatrice.jpg");
        template1=Imgcodecs.imread(filePath+"da_trovare.jpg");
        //Crea una matrice per l'immagine in uscita
        Mat outputImage=new Mat();
        //assegna la funzione matematica usata per il riconoscimento
        //https://docs.opencv.org/master/df/dfb/group__imgproc__object.html
        int machMethod=Imgproc.TM_CCOEFF;
        //Template matching method
        //passiamo l'immagine intera, l'immgine da ricercare, l'immagine da creare, la funzione matematica
        Imgproc.matchTemplate(source, template1, outputImage, machMethod);
        //ritorna il punto massimo
        MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc=mmr.maxLoc;
        
        //Disegna l'immagine passando il punto preso nelle righe precedenti e
        //la matrice dell'immagine template
        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template1.cols(),
        matchLoc.y + template1.rows()), new Scalar(0, 0, 255));
        Imgcodecs.imwrite(filePath+"Trovato.jpg", source);
        System.out.println("Completato");

      
    }
}
