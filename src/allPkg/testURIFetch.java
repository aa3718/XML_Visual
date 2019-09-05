package allPkg;

import org.apache.jena.query.*;

// Query attempt

class testURIFetch {
    public static void main(String args[])
    {

        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);


        String s2 =
                "SELECT ?p ?o \n" +
                "WHERE\n" +
                "  { <http://vocab.getty.edu/tgn/1000080> ?p ?o .\n" +
                "  }\n";

        Query query = QueryFactory.create(s2); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "http://vocab.getty.edu/sparql?force=true", query );
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query) ;

        while (results.hasNext()) {

        }


/*
        String s2 =
                "SELECT ?p ?o \n" +
                        "WHERE\n" +
                        "  { <http://www.w3.org/ns/odrl/2/assigner> ?p ?o .\n" +
                        "  }\n";

        Query query = QueryFactory.create(s2); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "https://www.w3.org/ns/odrl/2/sparql?force=true", query );
        ResultSet results = (ResultSet) qExe.execSelect();
        ResultSetFormatter.out(System.out, (org.apache.jena.query.ResultSet) results, query) ;
*/

        /*
        // uri  object
        URI uri = null;

        try {
            // create a URI
            uri = new URI("http://vocab.getty.edu/tgn/1000080");

            // get the  Query
            String _Query = uri.getQuery();

            // display the URL
            System.out.println("URI = " + uri);

            // display the  Query
            System.out.println(" Query= " + _Query);
        }
        // if any error occurs
        catch (Exception e) {
            // display the error
            System.out.println(e);
        }
        */
    }
}
