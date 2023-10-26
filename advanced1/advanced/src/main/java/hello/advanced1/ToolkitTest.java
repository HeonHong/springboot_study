package hello.advanced1;

import java.awt.*;

public class ToolkitTest {
    public static void main(String[]args ){
      Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {
              for(int i=0;i<5;i++){
                  Toolkit toolkit = Toolkit.getDefaultToolkit();
                  toolkit.beep();

                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                  }

              }
          }
      });

      thread.start();


        for(int i=0;i<5;i++){
            System.out.println("비프");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
