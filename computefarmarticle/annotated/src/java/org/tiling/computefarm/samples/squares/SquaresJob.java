package org.tiling.computefarm.samples.squares;

import org.tiling.computefarm.CancelledException;
import org.tiling.computefarm.CannotTakeResultException;
import org.tiling.computefarm.CannotWriteTaskException;
import org.tiling.computefarm.ComputeSpace;
import org.tiling.computefarm.Job;

public class SquaresJob implements Job {

    private final int n;
    private int sum;

    public SquaresJob(int n) {
        this.n = n;
    }

    public void generateTasks(ComputeSpace space) {
        for (int i = 1; i <= n; i++) {
            try {
                space.write(new SquaresTask(i));
            } catch (CannotWriteTaskException e) {
                break;
            } catch (CancelledException e) {
                break;
            }
        }
    }

    public void collectResults(ComputeSpace space) {
        sum = 0;
        for (int i = 1; i <= n; i++) {
            try {
                Integer result = (Integer) space.take();
                sum += result.intValue();
            } catch (CannotTakeResultException e) {
                break;
            } catch (CancelledException e) {
                sum = 0;
                break;
            }
        }
    }

    public int getSumOfSquares() {
        return sum;
    }

}