function exportTableToExcel(tableID, filename = '') {
    var downloadLink;
    var file_name=document.getElementById("xlsFilename").innerHTML;
    filename=file_name;
    
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);

    try {
        var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    } catch (err) {
        alert('Table is empty');
        return;
    }
    // Specify file name
    filename = filename ? filename + '.xls' : 'records.xls';

    // Create download link element
    downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);

    if (navigator.msSaveOrOpenBlob) {
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob(blob, filename);
    } else {
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;

        // Setting the file name
        downloadLink.download = filename;

        //triggering the function
        downloadLink.click();
}
}