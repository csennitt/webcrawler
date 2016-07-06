# webcrawler
Initial version of a web crawler




**up and running**
you must have both maven ( i used 3.3.9 ) and java ( i used 1.7 ) installed and configured for the path

once the source is downloaded
navigate the the source folder
(you should see a pom.xml file in that directory)


build it by executing the following 
	mvn clean package

This will have created a <target> directory

**To execute**

	cd target
	java -jar webcrawler-0.1-jar-with-dependencies.jar http://www.theregister.co.uk

this will start processing www.theregister.co.uk


**usage and limitations**
The current version requires that you have a fully url - including the http:// portion
This version is hard coded for a maximum of 50 pages - mainly because i tested against the bbc - which is a vast web site and debugging takes longer... 

The code does NOT handle network errors - further work to have an "Error Page" is required.

**Implementation** details
I wanted to avoid recusion - recusion is ok in scala / clojure - but Java does not have any tail call optimisation.

To achieve this a there is one i used the "Processor" object which has a mutating collection of completed pages and owns a UrlTracker object 
The "UrlTracker" class owns two mutating Sets which track progress along with a Queue where further requests are added and consumed.
a good todo is to move the "pages" variable into the urlTracker - so ALL mutating variables are in a single object.

Yes - I used RegEx to parse html - I wanted to keep the number of external packages to a minimum
the regex implemetation of Page_Parser is reasonably performant - a "proper" html parser would probably be less so.

I have not handled Proxy settings - anyone running this behind a corporate firewill will suffer.
not a big job but time limited


**ToDo**
currently static url's are not parsed - this is a simple of implementing in the PageParser ( SimplePageParser implementation ) vie an additional regex

Unit Tests - I started this in a TDD manner - this was extremely usefull when ensuring the more complex parts worked ( the page parser and  Page classes )
I then broke most of the other class tests when during a major refactor ( removing and add new classes ) !! I made the code work quickly ( for a demo ) and removed those class tests ( along with the classes )
Additional unit tests need adding before any more work.

output should be configurable - currently hard coded as string buffer system.out the Processor class should know nothing about formatting output - currently it does !!

currently a stupid file name for Fat Jar - needs creating with a sensible name

needs a throttling mechanism - some web site will stop crawlers

robots.txt - no support for robots.txt- currently crawls all web links.

Make end use friendly - add HTTP:// to the command line
