/**
 * 
 */
 
 
 
  let submitButton = document.getElementById("submit");
        let userInput = document.getElementById("user-input");
        let canvas = document.getElementById("canvas");
        let reloadButton = document.getElementById("reload-button");
        let text = "";
        //Generate Text
        const textGenerator = () => {
          let generatedText = "";
          /* String.fromCharCode gives ASCII value from a given number */
          // total 9 letters hence loop of 3
          for (let i = 0; i < 2; i++) {
            //65-90 numbers are capital letters
            generatedText += String.fromCharCode(randomNumber(65, 90));
            //97-122 are small letters
            generatedText += String.fromCharCode(randomNumber(97, 122));
            //48-57 are numbers from 0-9
            generatedText += String.fromCharCode(randomNumber(48, 57));
          }
          return generatedText;
        };
        //Generate random numbers between a given range
        const randomNumber = (min, max) =>
          Math.floor(Math.random() * (max - min + 1) + min);
        //Canvas part
        function drawStringOnCanvas(string) {
          //The getContext() function returns the drawing context that has all the drawing properties and functions needed to draw on canvas
          let ctx = canvas.getContext("2d");
          //clear canvas
          ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
          //array of text color
          const textColors = ["rgb(0,0,0)", "rgb(130,130,130)"];
          //space between letters
          const letterSpace = 150 / string.length;
          //loop through string
          for (let i = 0; i < string.length; i++) {
            //Define initial space on X axis
            const xInitialSpace = 25;
            //Set font for canvas element
            ctx.font = "20px Roboto Mono";
            //set text color
            ctx.fillStyle = textColors[randomNumber(0, 1)];
            ctx.fillText(
              string[i],
              xInitialSpace + i * letterSpace,
              randomNumber(25, 40),
              100
            );
          }
        }
        //Initial Function
        function triggerFunction() {
          //clear Input
          userInput.value = "";
          text = textGenerator();
          console.log(text);
          //Randomize the text so that everytime the position of numbers and small letters is random
          text = [...text].sort(() => Math.random() - 0.5).join("");
          drawStringOnCanvas(text);
        }
        //call triggerFunction for reload button
        reloadButton.addEventListener("click", triggerFunction);
        //call triggerFunction when page loads
        window.onload = () => triggerFunction();
        //When user clicks on submit
        submitButton.addEventListener('click', function() {
            if (userText.value === c) {
                document.getElementById("submit").style.display = "initial";
                output.innerHTML = "";
            }
            else{document.getElementById("submit").style.display = "none";
            output.innerHTML = "Incorrect, please try again";}
        });
        
        
       
        
    function onSubmit(token) {
        document.getElementById("demo-form").submit();
      }
    function validateUsername(){
        var username = document.getElementById("username").value;
        var valid = new RegExp("^[A-Aa-z]{1}[A-Za-z _-]{3,}").test(username);
        if(valid){document.getElementById("username").style.backgroundColor = "#ddffdd";}
        else{document.getElementById("username").style.backgroundColor = "#ffdddd";}
    }
    function validateEmail(){
        var userEmail = document.getElementById("emailId").value;
        var valid = new RegExp("/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/").test(userEmail);
        if(valid){document.getElementById("emailId").style.backgroundColor = "#ddffdd";}
        else{document.getElementById("emailId").style.backgroundColor = "#ffdddd";}
    }
    function validatePassword(){
        var password = document.getElementById("password").value;
        var valid = new RegExp("(?=(.*[a-z]){2,})(?=(.*[A-Z]){2,})(?=(.*[0-9]){2,})(?=(.*[^A-Za-z0-9<>;&]){2,})").test(password);
        if(valid){document.getElementById("password").style.backgroundColor = "#ddffdd";}
        else{document.getElementById("password").style.backgroundColor = "#ffdddd";}
    }
    function confirmPassword(){
         if(document.getElementById("password").value == document.getElementById("confirm").value)
         {document.getElementById("confirm").style.backgroundColor = "#ddffdd";}
        else{document.getElementById("confirm").style.backgroundColor = "#ffdddd";}
    }