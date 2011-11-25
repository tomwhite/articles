public class NonTerminatingJob implements Job {

    public void generateTasks(ComputeSpace computeSpace) {
    }
    public void collectResults(ComputeSpace computeSpace) {
        System.out.println("Collecting results...");
		try {
		    computeSpace.take();
		} catch (CannotTakeResultException e) {
		    e.printStackTrace();
		} catch (CancelledException e) {
	        System.out.println("Job cancelled.");
        }                
    }

}