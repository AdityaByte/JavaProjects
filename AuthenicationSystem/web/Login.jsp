<!DOCTYPE html>
<html>
    <head>
        <title>Login In</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Hind:wght@300;400;500;600;700&family=Inter:wght@100..900&display=swap');
    
        body{
            margin: 0;
            padding: 0;
            height: 100vh;
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            font-family: "Hind", sans-serif;
            font-weight: 400;
            font-style: normal;
        }

        body h2{
            font-size: 5ch;
            letter-spacing: 1px;
        }

        body #userform{
            display: grid;
            grid-template-columns: auto;
            grid-template-rows: repeat(5 , auto);
            border: 1px solid #80808080;
            background-color: #F3F7FC;
            padding: 40px;
            width: 35%;
            height: 34vh;
            border-radius: 8px;
        }


        body #userform .form-group{
            display: flex;
            flex-direction: column;
            align-items: center;
            column-gap: 2px;
        }

        #userform .form-group label{
            font-weight: 500;
            align-self: flex-start;
        }


    
        #userform .form-group input{
            padding: 9.07px 16.13px;
            border: 1px solid #80808080;
            gap: 10.08px;
            border-radius: 4px;
            background-color: #FFFFFF;
            width: 95%;
        }

        #userform button{
            align-self: center;
            width: 30%;
            height: 40px;
            color: white;
            line-height: 25.82px;
            font-weight: 700;
            text-align: center;
            font-size: 14.13px;
            padding: 9.07px, 16.13px, 9.07px, 16.13px;
            background-color: #0A66C2;
            border: 0.58px #FFFFFF;
            gap: 10.08px;
            border-radius: 4.03px;
            margin: 15px 0px;
        }
    </style>
    <body>
        <h2>Log In</h2>
        <form name="userform" id="userform" onsubmit="return validateForm(event)">
            <div class="form-group">
                <label for="email">Email: </label>
                <input id="email" type="email" name="email" required/>
            </div>
            <div class="form-group">
                <label for="pass">Password: </label>
                <input id="pass" type="password" name="pass" required/>
            </div>
            <span id="msg" style="font-weight: 500; color: green; padding: 2px 4px"></span>
            <button type="submit">Login</button>
            <a href="forgetform.jsp">Forget Password</a>
        </form>
        
        
        <span>Don't have an account? <a href="Signup_ma.html">Sign up</a></span>
        
    </body>
    
    <script>
        
    
        function validateForm(event){
            event.preventDefault();
            let email = document.getElementById("email").value;
            let password = document.getElementById("pass").value;
            
            if(email != null && password != null && email != "" && password != ""){
                document.getElementById("msg").innerHTML = "Checking Details";
                checkValue();
                return true;
            }else{
                document.getElementById("msg").innerHTML = "Please check the values that you have entered";
                document.getElementById("msg").style.color = "red";
                return false;
            }
        }
        
        
        function checkValue(){
            var email = $("#email").val();
            var pass = $("#pass").val();
            console.log("yeah i am in the ajax func");
            
            $.ajax({
                url: 'LoginInServlet',
                type: 'POST',
                data: {email:email, pass:pass},
                success: function(response){
                    console.log(response);
                    if(response.status === "success"){
                        window.location.href = "Home.jsp";
                    }
                    else{
                        document.getElementById("msg").innerHTML = "Invalid Username or password";
                        document.getElementById("msg").style.color = "red";
                    }
                }
            });
        }
        
    </script>
    
</html>
