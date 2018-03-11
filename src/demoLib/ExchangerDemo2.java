package demoLib;

import java.util.concurrent.Exchanger;

class ExchangerRunnable implements Runnable{

    Exchanger exchanger = null;
    Object    transferObj    = null;

    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.transferObj = object;
    }

    public void run() {
        try {
            Object previous = this.transferObj;

            this.transferObj = this.exchanger.exchange(this.transferObj);

            System.out.println(
                    Thread.currentThread().getName() +
                    " exchanged " + previous + " for " + this.transferObj
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ExchangerDemo2 {

	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<String>();

		ExchangerRunnable exchangerRunnable1 =
		        new ExchangerRunnable(exchanger, "A");

		ExchangerRunnable exchangerRunnable2 =
		        new ExchangerRunnable(exchanger, "B");
		ExchangerRunnable exchangerRunnable3 =
		        new ExchangerRunnable(exchanger, "C");

		 new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();
		new Thread(exchangerRunnable3).start();

	}

}
