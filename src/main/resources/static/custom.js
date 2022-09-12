
let count = 1;
function add_more(value){
    console.log(value);
    count++;
    let html='<input type="hidden" name="attributeId">' +
        '       <div class="card mb-2" id="product_attr_'+count+'">\n' +
        '                <div class="card-body">\n' +
        '                    <div class="form-group">\n' +
        '                        <div class="row">\n' ;
    let productSize = jQuery('.productSize').html();
    productSize = productSize.replace("selected","");
    html+=                           '<div class="col-md-4">\n'+
        '                                <label class="control-label mb-1"> Size</label>\n'+
        '                                <select class="form-control productSize" name="attrSize">\n' +
        productSize +
        '                                </select>\n' +
        '                            </div>\n' +
        '                            <div class="col-md-4">\n' +
        '                                <label class="control-label mb-1">Quantity</label>\n' +
        '                                <input type="text" class="form-control productQty"  name="attrQty" required>\n' +
        '                            </div>\n' +
        '                            <div class="col-md-4">\n' +
        '                                <label class="control-label mb-1">MRP</label>\n' +
        '                                <input type="text" class="form-control productMrp"  name="attrMrp" required>\n' +
        '                            </div>\n' +
        '                            <div class="col-md-4">\n' +
        '                                <label  class="control-label mb-1">Price</label>\n' +
        '                                <input type="text" class="form-control productPrice"  name="attrPrice" required>\n' +
        '                            </div>\n' +
        '                            <div class="col-md-4 mt-4">\n' +
        '                                <label class="control-label mb-1">Action</label>\n' +
        '                                <button type="button" class="btn btn-danger btn-md" onclick=remove_more("'+count+'")>\n' +
        '                                    <i class="fa fa-plus">&nbsp;Remove</i></button>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>';
    jQuery('#product_attr_box').append(html);
}
function remove_more(loop_count){
    jQuery('#product_attr_'+loop_count).remove();
}
$('#productImage').change(function (){
    showThumbnailImage(this);
});
function showThumbnailImage(fileInput){
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function (e){
        $('.thumbnail').attr('src',e.target.result);
    };
    reader.readAsDataURL(file);
}