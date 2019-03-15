function filterTable() {
    
    var dropDownBox=document.getElementById("filterchoiceLocal");
    var filterChoice=dropDownBox.options[dropDownBox.selectedIndex].value;
    var colIndex=0; // default
    
    if(filterChoice=="2"){    // (Date in AttendanceRecords Table) & (First Name in Employee Table)
        colIndex=1;
    }else if(filterChoice=="3"){    // LastName in employeeRecords Table
        colIndex=3;
    }else if(filterChoice=="4"){    // LastName in employeeRecords Table
        colIndex=7;
    }
    
    // Declare variables 
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filterByEID");
    filter = input.value.toUpperCase();
    table = document.getElementById("tblData");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        
        
        td = tr[i].getElementsByTagName("td")[colIndex];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function toggleAdvanceFilters() {
  var x = document.getElementById("advanceFilters");
  
  if (x.style.display == "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}