Ext.define('DEMO.controller.UploadController', {
    extend	: 'Ext.app.Controller',

    stores	: ['UploadDocListStore'],
    models	: ['UploadDocLookupModel'],

    views	: [
		'upload.UploadDocList','upload.UploadPanel'
    ],
    refs: [{
	        ref: 'UploadDocList',
	        selector: 'UploadDocList'
	    }
    ],
    init	: function() {
        this.control({
			'UploadDocList' : {
				show		: this.loadList
				
			},
			'UploadDocList button[action=query]': {
				click: this.query
            },
			'UploadPanel form button[action=upload]': {
				click: this.upload
            },
            'UploadDocList actioncolumn': {
				click: this.onAction
            }
            
        });
    },

	// Reload the list every time it is shown
    loadList	: function(list) {
    	//不执行alert(1);
    	list.getStore().load();
    },
    upload:function( e, eOpts){
     var form = e.up('form').getForm(),
         store =  this.getUploadDocListStoreStore();
     if(form.isValid()){
         form.submit({
             url: 'upload/upload',
             waitMsg: '上传中...',
             success: function(form, action) {
            		 Ext.MessageBox.show({
                         title: '提示',
                         msg: action.result.message,
                         icon: Ext.MessageBox.INFO,
                         buttons: Ext.Msg.OK,
                         fn: function(buttonId) {
                             if (buttonId === "ok") {
                            	store.reload();
                             }
                         }
                     });
            	 
             },
             failure: function(form, action) {//form(success:false) 和 ajax(通信故障) 区别
            	 console.log(action);
            	 Ext.MessageBox.show({
                     title: '提示',
                     msg: action.result.message,
                     icon: Ext.MessageBox.ERROR,
                     buttons: Ext.Msg.OK
                 });
             }
         });
     }
   },
   query:function( e, eOpts){
//    	var officeId = e.up('toolbar').down('[name=officeId]').getValue();
//    	var zbxMc = e.up('toolbar').down('[name=zbxMc]').getValue();
//    	var status = e.up('toolbar').down('[name=status]').getValue();
//    	var from = e.up('toolbar').down('[name=from_date]').getValue();
//    	console.log();
//    	var to = e.up('toolbar').down('[name=to_date]').getValue();
//    	this.getUploadDocListStoreStore().load({
//    		params:{
//    			officeId:officeId,
//    			zbxMc: zbxMc,
//    			status:status,
//    			from:Ext.Date.format(from,'Y-m-d'),
//    			to:Ext.Date.format(to,'Y-m-d')
//    		} 
//    	});
	   this.getUploadDocListStoreStore().load();
    },
    onAction: function(view,cell,row,col,e){
        var m = e.getTarget().className.match(/\bicon-(\w+)\b/)
        if(m){
            //选择该列
            this.getUserList().getView().getSelectionModel().select(row,false)
            switch(m[1]){
                case 'edit':
                	var record = this.getUserListStoreStore().getAt(row);
                	var editWin = Ext.widget('UserEdit').show();
                	editWin.setTitle('修改用户');
                	if(record){
//                		editWin.down('form').getForm().findField('loginName').set({action:'edit'});
                		Ext.apply(Ext.getCmp('loginName'), {action:'edit'}, {});
                		Ext.apply(Ext.getCmp('loginName'), {myrawValue:record.get('loginName')}, {});
                		editWin.down('form').loadRecord(record);
                	}
                    break;
                case 'del':
                	var record = this.getUserListStoreStore().getAt(row);
                	var editWin = Ext.widget('PwdEdit').show();
                	editWin.setTitle('修改密码');
                	if(record){
                		editWin.down('form').loadRecord(record);
                	}
                    break;
                case 'view':
                	var record = this.getUserListStoreStore().getAt(row);
                	var editWin = Ext.widget('PwdEdit').show();
                	editWin.setTitle('修改密码');
                	if(record){
                		editWin.down('form').loadRecord(record);
                	}
                    break;
               default:
            	   break;
                    
            }
        }
    }

});
