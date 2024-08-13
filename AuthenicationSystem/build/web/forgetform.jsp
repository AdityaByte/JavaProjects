<!DOCTYPE html>
<html>
    <head>
        <title>Forget Password</title>
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
            grid-template-rows: repeat(3 , auto);
            border: 1px solid #80808080;
            background-color: #F3F7FC;
            padding: 40px;
            width: 35%;
            height: 20vh;
            border-radius: 8px;
        }
        body #otpform{
            display: grid;
            grid-template-columns: auto;
            grid-template-rows: repeat(3 , auto);
            border: 1px solid #80808080;
            background-color: #F3F7FC;
            padding: 40px;
            width: 25%;
            height: 20vh;
            border-radius: 8px;
        }
        body #passwordform{
            display: grid;
            grid-template-columns: auto;
            grid-template-rows: repeat(4 , auto);
            border: 1px solid #80808080;
            background-color: #F3F7FC;
            padding: 40px;
            width: 35%;
            height: 30vh;
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
        

        body #otpform .form-group{
            display: flex;
            flex-direction: column;
            align-items: center;
            column-gap: 2px;
        }

        #otpform .form-group label{
            font-weight: 500;
            align-self: flex-start;
        }


    
        #otpform .form-group input{
            padding: 9.07px 16.13px;
            border: 1px solid #80808080;
            gap: 10.08px;
            border-radius: 4px;
            background-color: #FFFFFF;
            width: 95%;
        }

        #otpform button{
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
        

        body #passwordform .form-group{
            display: flex;
            flex-direction: column;
            align-items: center;
            column-gap: 2px;
        }

        #passwordform .form-group label{
            font-weight: 500;
            align-self: flex-start;
        }


    
        #passwordform .form-group input{
            padding: 9.07px 16.13px;
            border: 1px solid #80808080;
            gap: 10.08px;
            border-radius: 4px;
            background-color: #FFFFFF;
            width: 95%;
        }

        #passwordform button{
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
        <h2>Forgot Password</h2>
        <form name="userform" id="userform" onsubmit="return validateForm(event)">
            <div class="form-group">
                <label for="email">Email: </label>
                <input id="email" type="email" name="email" required/>
            </div>
            <span id="msg" style="font-weight: 500; color: green; padding: 2px 4px"></span>
            <button type="submit">Forget Password</button>
        </form>
        <form name="otpform" id="otpform" onsubmit="return validateOTP(event)" style="display: none;">
            <div class="form-group">
                <label for="otp">OTP: </label>
                <input id="otp" type="number" name="otp" required/>
            </div>
            <span id="msg1" style="font-weight: 500; color: red; padding: 2px 4px"></span>
            <button type="submit">Submit OTP</button>
        </form>
        
        <form name="passwordform" id="passwordform" onsubmit="return validatePassword(event)" style="display: none;">
            <div class="form-group">
                <label for="otp">New Password: </label>
                <input id="pass" type="text" name="pass" required/>
            </div>
            <div class="form-group">
                <label for="otp">Confirm New Password: </label>
                <input id="cpass" type="text" name="cpass" required/>
            </div>
            <span id="msg2" style="font-weight: 500; color: red; padding: 2px 4px"></span>
            <button type="submit">Create New Password</button>
        </form>
    </body>
    
    <script>
        
        function validatePassword(event){
            event.preventDefault();
            let pass = document.getElementById("pass").value;
            let cpass = document.getElementById("cpass").value;
            
            if(pass === null || cpass === null || pass === "" || cpass === ""){
                document.getElementById("msg2").innerHTML = "Please fill out all the fields";
                return false;
            }
            else if(pass !== cpass){
                document.getElementById("msg2").innerHTML = "Passwords are Different";
                return false;
            }
            else{
                changePassword();
                return true;
            }
        }
        
        function changePassword(){
            let pass = $("#pass").val();
            
            $.ajax({
                url: 'changePassword',
                type: 'POST',
                data: {pass: pass},
                success: function(response){
                    if(response.status === "success"){
                        alert("Password is changed successfully");
                    }
                    else{
                        alert("some error occured at last step");
                    }
                }
            });
        }
        
        function validateOTP(event){
            event.preventDefault();
            console.log("inside the otp form");
            let otp = document.getElementById("otp").value;
            if(otp !== null){
                checkOTP();
                return true;
            }
            else{
                return false;
            }
        }
        
        function checkOTP(){
            let otp = $("#otp").val();
             $.ajax({
                 url: 'checkOTP',
                 type: 'POST',
                 data: {otp : otp},
                 success: function(response){
                     if(response.status === "success"){
                        document.getElementById("msg1").style.color = "green";
                        document.getElementById("msg1").innerHTML = "Invalid OTP";   
                        $("#otpform").hide();
                        $("#passwordform").show(); 
                     }
                     else{
                      document.getElementById("msg1").innerHTML = "Invalid OTP";   
                     }
                 }
             });
        }
        
        function validateForm(event){
            event.preventDefault();
            let email = document.getElementById("email").value;
            
            if(email === null || email === ""){
                alert("fill out the fields");
                return false;
            }
            else{
                forgetPassword();
                return true;
            }
        }
        
        function forgetPassword(){
            let email = $("#email").val();
            $.ajax({
                url: 'forgetPassword',
                type: 'POST',
                data: {email : email},
                success: function(response){
                    if(response.status === "success"){
                        alert("OTP is sent successfully to your email");
                        $("#userform").hide();
                        $("#otpform").show();
                    }
                    else if(response.status === "failure"){
                        document.getElementById("msg").innerHTML = "Email doesn't exists in the database";
                    }
                    else{
                        alert("Some error occured while sending email");
                    }
                }
            });
        }
    </script>
    
</html>
