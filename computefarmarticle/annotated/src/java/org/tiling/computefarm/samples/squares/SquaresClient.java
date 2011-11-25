package org.tiling.computefarm.samples.squares;

import org.tiling.computefarm.JobRunner;
import org.tiling.computefarm.JobRunnerFactory;
import org.tiling.computefarm.impl.javaspaces.util.ClasspathServer;

public class SquaresClient {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println(
                "Usage: java " + SquaresClient.class.getName() + " &lt;n&gt;");
            System.exit(1);
        }

        <b>ClasspathServer server = new ClasspathServer();
        server.start();</b>

        int n = Integer.parseInt(args[0]);
        SquaresJob job = new SquaresJob(n);
        JobRunnerFactory factory = <b>JobRunnerFactory.newInstance();</b>
        JobRunner jobRunner = factory.newJobRunner(job);
        jobRunner.run();

        System.out.println("n = " + n);
        System.out.println("Sum of squares = " + job.getSumOfSquares());
        System.out.println("n * (n + 1) * (2 * n + 1) / 6 = "
                + (n * (n + 1) * (2 * n + 1) / 6));

    }

}