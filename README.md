web-file-manager
================

A simple application to view server files structured as a tree

RUN: 

mvn jetty:run
Then open the http://localhost:8080/filemanager/index.html

to reuse the functionality the war should be deployed, css and js applied and the following script run:

  $(document).ready(function() {
            $(parentDiv).fileManager({rootDirectory:'', url:'${pageContext.request.contextPath}/filemanager'});
        });</script>

where rootDirectory - path to the directory or empty (root directory)
      url - filemanager app url
      parentDiv - div element to build the tree