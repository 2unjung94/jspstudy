/**
 * 
 */

/* .do로 끝나는 URLMapping 주소는 하나의 Servlet으로 다룬다. Servlet의 URLMapping을 *.do로 한다. (Servlet 만들때는 / 없어야함)
   suffix : prefix와 반대, 지정하는 이름은 마음대로 해도 되지만 .do가 가장 많이 사용된다.    
*/
  
  const getContextPath = ()=>{
    
    const host = location.host; /* localhost:8080 */
    const url = location.href; /* http://localhost:8080/mvc/getDate.do */
    /* /mvc의 슬래시 위치 찾기
    1. 호스트의 시작 위치 찾기.(indexOf) 2. 길이를 구한다(length) 3. 호스트의 시작위치 + 호스트 길이 */
    const begin = url.indexOf(host) + host.length;
    
    /* /getDate.do 찾기 */
    const end = url.indexOf('/', begin + 1);
    
    return url.substring(begin, end); // begin 포함, end 위치 앞까지
  }
  
 const getDateTime = ()=>{
   const type = document.getElementById('type');
   if(type.value === 'date'){
     location.href = getContextPath() + '/getDate.do';
   } else if(type.value === 'time'){
     location.href = getContextPath() + '/getTime.do';
   } else if(type.value === 'datetime'){
     location.href = getContextPath() + '/getDateTime.do';
   }
 }
 
 document.getElementById('btn').addEventListener('click', getDateTime);