function goListProduct() {
    $.ajax({
        type: 'GET',
        url: 'product/list',
        success: function() {
            alert('exito')
        }
    });
}

