package org.tiling.computefarm.samples.squares;

import java.io.Serializable;

import org.tiling.computefarm.Task;

public class SquaresTask implements Serializable, Task {

    private final int k;

    public SquaresTask(int k) {
        this.k = k;
    }

    public Object execute() {
        return new Integer(k * k);
    }

}