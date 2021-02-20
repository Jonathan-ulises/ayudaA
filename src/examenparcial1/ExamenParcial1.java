/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenparcial1;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Jonathan Ulises
 */
public class ExamenParcial1 {

    /**
     * @param args the command line arguments
     */
    static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    static PanamaHitek_MultiMessage multi = new PanamaHitek_MultiMessage(2, ino);
    static SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            
            try {
                if(multi.dataReceptionCompleted()){
                    System.out.print("Angulo Servo: " + multi.getMessage(0) + "!!!!!");
                    System.out.println("Intencidad LED: " + multi.getMessage(1));
                    multi.flushBuffer();
                }
            } catch (ArduinoException ex) {
                Logger.getLogger(ExamenParcial1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SerialPortException ex) {
                Logger.getLogger(ExamenParcial1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    };
    
    public static void main(String[] args) {
        try {
            ino.arduinoRX("COM3", 115200, listener);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(ExamenParcial1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
