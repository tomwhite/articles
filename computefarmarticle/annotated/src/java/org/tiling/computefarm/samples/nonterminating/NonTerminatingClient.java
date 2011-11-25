public class NonTerminatingClient {
	
	public static void main(String[] args) throws Exception {
		ClasspathServer server = new ClasspathServer();
		server.start();
		
		final NonTerminatingJob job = new NonTerminatingJob();
		final JobRunner jobRunner = JobRunnerFactory.newInstance().newJobRunner(job); 
		Thread jobRunnerThread = new Thread() {
		    public void run() {
				jobRunner.run();
		    }
		};
		jobRunnerThread.start();
	    System.out.println("Waiting 5 seconds before cancelling job...");
	    Thread.sleep(5000);
	    System.out.println("Cancelling job...");
		jobRunner.cancel();
	    System.out.println("Waiting for job runner to finish...");
		jobRunnerThread.join();
	    System.out.println("Job runner finished.");
	}
	
}
