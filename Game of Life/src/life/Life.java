package life;

import java.util.concurrent.atomic.AtomicBoolean;

public class Life extends Thread {

    private final AtomicBoolean lock;
    private final Object mutex;

    private LifeController lifeController;

    public Life(LifeController lifeController) {
        lock = new AtomicBoolean(false);
        mutex = new Object();
        this.lifeController = lifeController;
    }

    @Override
    public void run() {
        action();
    }

    private void action() {
        lifeController.disablePlayButton(true);
        for (int i = 0; i < lifeController.loops; i++) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            if (lock.get()) synchronized(mutex) {
                try {
                    mutex.wait();
                } catch (InterruptedException ex) {
                    break;
                }
            }
            try {
                Thread.sleep(lifeController.speed);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                break;
            }
            lifeController.universe.doLoop();
            lifeController.updateGUI();
        }
        lifeController.disablePlayButton(false);
    }

    public boolean isLocked() {
        return lock.get();
    }

    public void lock() {
        lock.set(true);
    }

    public void unlock() {
        lock.set(false);

        synchronized(mutex) {
            mutex.notify();
        }
    }
}
