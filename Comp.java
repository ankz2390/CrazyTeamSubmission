package com.comp;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * This is a sample class to launch a rule.
 */
public class Comp {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            // Initialize all the mean monsters
            Machine slowpoke=new Machine(2,5,"none",800);
            //Its 2013! Speed up boy
            Machine mr_fancy=new Machine(8,3,"average",1500);
            //Mr. fancy B-)
            Machine grandpa=new Machine(1,1,"none",400);
            //3, grandpa for its config
            Machine boss=new Machine(16,4,"premium",3000);
            //4, The boss
            Machine iron_throne=new Machine(35,5,"none",8000);
            //5, the 7 kingdoms are at war for this one #GameOfThrones
            Machine confused_machine=new Machine(2,2,"premium",5000);
            //6, This doesn't know who he is!
            ksession.fireAllRules();
            //Funny names are fun but it makes iterating difficult :) 
            
            if(slowpoke.risky!=true) {
            	System.out.println("Its Safe to use slowpoke!");
            }
            //Similarly code for all the machines and check
            
            if(slowpoke.crunchNumbers==true && slowpoke.risky==true) {
            	System.out.println("Slowpoke is a numbercruncher and is also risky!");
            }	
            
            if(slowpoke.hot!=true) {
            	System.out.println("Slowpoke is HOT :D !");
            }
            
            //Check whether confused_machine is hot, risky or a number cruncher
            
            
            logger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("ComputerRules.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

    public static class Machine {

        public String vcard;
        public int speed, memory,cost;

        private boolean loud=false, hot=false, cheap=false, game=false, crunchNumbers=false,risky=false;
        
        public Machine(int m, int s, String v, int c) {
        	speed=s;
        	memory=m;
        	vcard=v;
        	cost=c;
        }
        	
        public void setLoud() {
            loud=true;
        }

        public void setCheap() {
            cheap=true;
        }
        
        public void setGame() {
            game=true;
        }
        
        public void seHot() {
            hot=true;
        }
        
        public void setRisky() {
            risky=true;
        }
        public void Crunch() {
            crunchNumbers=true;
        }


    }

}
