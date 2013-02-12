/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm {

    private Vector<Process> jobs;

    PrioritySchedulingAlgorithm(){
	activeJob = null;
	jobs = new Vector<Process>();
	preemptive = false;
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
	jobs.add(p);
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
	if (p == activeJob)
	    activeJob = null;
	return jobs.remove(p);
    }

    public boolean shouldPreempt(long currentTime){
	return ((activeJob == null) || isPreemptive());
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(){
	Process p=null,loftiest=null;
	long priority=0, highest=0;
	
	for(int i=0; i < jobs.size(); ++i){
	    p = (Process) jobs.get(i);
	    priority = p.getPriorityWeight();
	    if( ( priority < highest ) || (i == 0) ){
		highest = priority;
		loftiest = p;
	    }
	}
	activeJob = loftiest;
	return activeJob;
    }

    public String getName(){
	return "Single-queue Priority";
    }
}