var jsondata=null;
$(function () {
        	
        	
        	// Code for getting available seats from Database
        	$.ajax({
			  url: "ParkingServlet",
			  type: "get",
			  // data:data
			  success: function (data){
				 // console.log(data);

				  var availableSlotNos = [];
				  
				  
				  for(var i = 0; i < data.length; i++) {
					  var slotNo = data[i].slotNo;
					  var available = data[i].available;
					  
					  //slotNos += slotNo +" : ";
					  
					  if(available == 0) {
					  	availableSlotNos.push(slotNo);
					  }
					  
				  }

				  init(availableSlotNos);
					
				  $('.' + settings.seatCss).on('click',function () {
						if ($(this).hasClass(settings.selectedSeatCss)){
							//do nothing
							//alert('This seat is already reserved');
						}
						else{
							
							$(this).siblings().removeClass(settings.selectingSeatCss);
							selectedSlot = $(this).find('a').text();
			                $(this).toggleClass(settings.selectingSeatCss);
							}
			            });

				  
				  $('#holder ul li').on('click', function (){
					  var jsonObj = [];
					  item = {};
					  item["slotNo"] = selectedSlot;
					  jsonObj.push(item);
					  var jsonData = "{slotNo:"+selectedSlot+"}";
					$.ajax({
		        			type: "POST",
		        			url: "BookingServlet",
		      			   data: {jsonData: JSON.stringify(item)},
		      			   dataType: "json",
		      			  success: function (data){
		      					console.log(data);
		      					jsondata=data;
								feedData(data);								
														
		      				  }
						
		        		});
		        	}); 
		        	
				 // console.log(availableSlotNos);
			  },
			  error: function (){
				console.log('error');  
			  },
			  dataType: "json"
			});

        	

            var settings = {
                rows: 2,
                cols: 10,
                rowCssPrefix: 'row-',
                colCssPrefix: 'col-',
                seatWidth: 50,
                seatHeight: 70,
                seatCss: 'seat',
                selectedSeatCss: 'selectedSeat',
				selectingSeatCss: 'selectingSeat'
            };

            var init = function (reservedSeat) {
                var str = [], seatNo, className;
                for (i = 0; i < settings.rows; i++) {
                    for (j = 0; j < settings.cols; j++) {
                        seatNo = (i + j * settings.rows + 1);
                        className = settings.seatCss + ' ' + settings.rowCssPrefix + i.toString() + ' ' + settings.colCssPrefix + j.toString();
                        if ($.isArray(reservedSeat) && $.inArray(seatNo, reservedSeat) != -1) {
                            className += ' ' + settings.selectedSeatCss;
                            
                            
                        }
                        str.push('<li class="' + className + '"' +
                                  'style="top:' + (i * settings.seatHeight).toString() + 'px;left:' + (j * settings.seatWidth).toString() + 'px">' +
                                  '<a title="' + seatNo + '">' + seatNo + '</a>' +
                                  '</li>');
                        
                    }
                }
                $('#place').html(str.join(''));
                
            };

            //case I: Show from starting
            init();

            function feedData(data)
            {
            	$('#bookingid').val(data.bookingId);
            	var dur=$('#duration').val();
            	$('#vehicletype').val(data.vehicle);
            	$('#floorno').val('1');
            	$('#slot').val(data.slot);
            	$('#rate').val(data.rate);
            	$('#total').val(dur*data.rate);
            	var paid=$('#paid').val();
            	var bal=paid-dur*parseFloat(data.rate);
            	
            }
            
            $('#pay').on('click', function (){
				 if($('#vehicleno').val().length==0)
					 alert("Please provide the vehicle number");
				 else
					 {
				 
					jsondata.vehicleno=$('#vehicleno').val();
					jsondata.duration=$('#duration').val();
				$.ajax({
	        			type: "POST",
	        			url: "PaymentServlet",
	      			   data: {jsondata: JSON.stringify(jsondata)},
	      			   dataType: "json",
	      			  success: function (data){
	      					alert("Database successfully updated.")	;						
	      					 $('#holder').find('ul').children('li').each(function (){
	      						if($(this).hasClass('selectingSeat')){
	      							$(this).removeClass('selectingSeat').addClass('selectedSeat');
	      						}
	      								
	      				  });
	      			  }
	        		});
					 }
	        	}); 
            
            
           
 
        });
    