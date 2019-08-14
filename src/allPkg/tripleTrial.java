package allPkg;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.IOException;


public class tripleTrial {

    public static void main(String[] args) throws IOException {
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("odrl:", "http://www.w3.org/ns/odrl/2/");
        //model.setNsPrefix("skos", "http://www.w3.org/2004/02/skos/core#");
        //model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type");

        model.read("/Users/Chapman/Documents/ODRLxmlTestFile/tur3.ttl");




/*
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);

        Model model = ModelFactory.createDefaultModel() ;
        model.read("/Users/Chapman/Documents/ODRLxmlTestFile/tur3.ttl");


        final String filename = "/Users/Chapman/Documents/ODRLxmlTestFile/tur3.ttl";

        PipedRDFIterator<Triple> iter = new PipedRDFIterator<>();
        final PipedRDFStream<Triple> inputStream = new PipedTriplesStream(iter);

        // PipedRDFStream and PipedRDFIterator need to be on different threads
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Create a runnable for our parser thread
        Runnable parser = new Runnable() {

            @Override
            public void run() {
                // Call the parsing process.
                RDFDataMgr.parse(inputStream, filename);
            }
        };

        // Start the parser on another thread
        executor.submit(parser);

        // We will consume the input on the main thread here

        // We can now iterate over data as it is parsed, parsing only runs as
        // far ahead of our consumption as the buffer size allows
        while (iter.hasNext()) {
            Triple next = iter.next();
            // Do something with each triple
            System.out.println("Subject:  " + next.getSubject());
            System.out.println("Object:  " + next.getObject());
            System.out.println("Predicate:  " + next.getPredicate());
            System.out.println("\n");
                }
                */
            }

        }



