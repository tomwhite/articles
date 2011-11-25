package uk.co.hapax;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

// dot -Tpng baa.dot > baa.png
public class AutomatonApp {

    public static void main(String[] args) {
        regex6();
    }
    
    private static void regex1() {
        RegExp re = new RegExp("baaa*!");
        Automaton automaton = re.toAutomaton();
        System.out.println(automaton.toDot());

        System.out.println("Deterministic: " + automaton.isDeterministic());
        System.out.println(automaton.run("baa!"));
    }
    
    private static void regex2() {
        RegExp re = new RegExp("baa*a!");
        Automaton automaton = re.toAutomaton();
        System.out.println(automaton.toDot());

        System.out.println("Deterministic: " + automaton.isDeterministic());
        System.out.println(automaton.run("baa!"));
        System.out.println(automaton.toDot());
    }    
    
    private static void regex3() {
        Automaton automaton = Automaton.makeChar('b')
        .concatenate(Automaton.makeChar('a'))
        .concatenate(Automaton.makeChar('a').repeat())
        .concatenate(Automaton.makeChar('a'))
        .concatenate(Automaton.makeChar('!'));

        System.out.println(automaton.toDot());
        
        System.out.println("Deterministic: " + automaton.isDeterministic());
        System.out.println(automaton.run("baa!"));
        System.out.println(automaton.run(""));
    }

    private static void regex4() {
        //RegExp re = new RegExp("a|ab");
        //Automaton automaton = re.toAutomaton();
		Automaton automaton = Automaton.makeChar('a').union(Automaton.makeString("ab"));
    	
        System.out.println(automaton.toDot());
        System.out.println("Deterministic: " + automaton.isDeterministic());
        
        automaton.determinize();
        
        System.out.println(automaton.toDot());
        System.out.println("Deterministic: " + automaton.isDeterministic());
        
        
        System.out.println(automaton.run("a"));
        System.out.println(automaton.run("ab"));
        System.out.println(automaton.run("z"));
        

    }
    
    private static void regex5() {
    	//"Greedy combinatorial explosion"
    	// Thanks to http://discuss.fogcreek.com/joelonsoftware5/default.asp?cmd=show&ixPost=154057&ixReplies=14
    	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab".matches("((a*)(a*))+b"));
    	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac".matches("((a*)(a*))+b"));
    	//System.out.println("=XX===========================================".matches("X(.+)*X"));
    }    
    
    private static void regex6() {
    	
        RegExp re = new RegExp("((a*)(a*))+b");
        Automaton automaton = re.toAutomaton();    	
        System.out.println(automaton.toDot());
        System.out.println(automaton.run("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"));
        System.out.println(automaton.run("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac"));
    }

}
