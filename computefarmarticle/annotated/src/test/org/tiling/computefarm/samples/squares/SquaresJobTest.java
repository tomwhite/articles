package org.tiling.computefarm.samples.squares;

import junit.framework.TestCase;

import org.tiling.computefarm.JobRunner;
import org.tiling.computefarm.JobRunnerFactory;
import org.tiling.computefarm.impl.simple.SimpleJobRunnerFactory;

public class SquaresJobTest extends TestCase {
    public void test() {
        int n = 10;
        SquaresJob job = new SquaresJob(n);
        JobRunnerFactory factory = new SimpleJobRunnerFactory();
        JobRunner jobRunner = factory.newJobRunner(job);
        jobRunner.run();
        assertEquals(n * (n + 1) * (2 * n + 1) / 6, job.getSumOfSquares());
    }
}
