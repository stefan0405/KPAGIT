<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 
   <%
       List li = StudentService.getInstance().getAllIndeks();

       String[] str = new String[li.size()];
       Iterator it = li.iterator();
 
       int i = 0;
       while(it.hasNext()){
           String p = (String)it.next();
           str[i] = p;
           i++;
       }
 
    //jQuery related start
       String query = (String)request.getParameter("q");
 
       int cnt=1;
       for(int j=0;j<str.length;j++)
       {
           if(str[j].toUpperCase().startsWith(query.toUpperCase()))
           {
              out.print(str[j]+"\n");
              if(cnt>=5)// 5=How many results have to show while we are typing(auto suggestions)
              break;
              cnt++;
            }
       }
%>