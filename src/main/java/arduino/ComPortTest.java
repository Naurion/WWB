package arduino;

import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class ComPortTest implements SerialPortEventListener {
    SerialPort serialPort;
    String PORT_NAME = "COM3";
    private BufferedReader input;
    private OutputStream output;
    public static final int TIME_OUT = 2000;
    public static final int DATA_RATE = 9600;

    public void initialize() {
        CommPortIdentifier portId = null;
        Enumeration portEnum;
        portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier corrPortId = (CommPortIdentifier) portEnum.nextElement();
            if (corrPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (corrPortId.getName().equals(PORT_NAME)) {
                    portId = corrPortId;
                }
            }
        }

        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();
            Thread.sleep(TIME_OUT);
            serialPort.addEventListener((this));
            serialPort.notifyOnDataAvailable(true);
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                System.out.println(inputLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void writeDataToArduino(String data) {
        try {
            output.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final ComPortTest main = new ComPortTest();
        main.initialize();
        Thread thread = new Thread() {
            public void run() {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String command = "";
                try {
                    while (command.equals("exit")) {
                        command = bufferedReader.readLine();
                        switch (command) {
                            case "on":
                                main.writeDataToArduino("1");
                                break;
                            case "off":
                                main.writeDataToArduino("0");
                                break;
                            case "exit":
                                main.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

}
