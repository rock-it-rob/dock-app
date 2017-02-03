<template lang="html">
  <div class="sortable-table">
	<div style="font-size:15px; margin: auto; outline-style:none;" v-if="rowInfoList && rowInfoList.length > 0" >
		<i class="fa fa-angle-double-left i-hvr" style="margin-top:10px" v-if="firstRec>0" v-on:click="prevPage()"></i>

		<span v-show="firstRec===0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>

		{{firstRec + 1}} - {{Math.min(lastRec + 1,rowInfoList.length)}} of {{rowInfoList.length}}

		<i class="fa fa-angle-double-right i-hvr" style="margin-top:10px;" v-if="rowInfoList && lastRec+1<rowInfoList.length" v-on:click="nextPage()"></i>

		<a class="btn btn btn-success export-excel-btn" v-show="exportExcelName" v-on:click="exportToExcel()">
					<i class="fa fa-file-excel-o"></i>&nbsp;Excel</a>
	</div>
	<p><p>
	<table class="table table-striped table-bordered table-hover small" id="table-sortable" >
	<thead>
		<tr>
			<th v-for="(header, index) in headerInfoList"
				v-bind:class="[{'sortDefault':header.sortable && !header.sortOrder,
 						   'sortAsc':header.sortOrder==='A',
 						   'sortDesc':header.sortOrder==='D'},
 						   header.headerClass]"
				v-on:click="sort(index)"
				class="columnHeader">
					{{header.value}}
			</th>
		</tr>
	</thead>
	<tbody>
		<tr v-for="row in displayList"
		    v-bind:class="rowHighlighting ? rowHighlighting(row) : ''"
		    v-on:click="rowOnClick ? rowOnClick(row) : ''">
			<td v-for="(rowData, index) in row">
				<span class="printNewlines" v-if="headerInfoList[index].onClick" v-html="rowData"></span>
        <editable v-if="headerInfoList[index].editFunction"
                  v-bind:initial="rowData"
                  v-bind:on-edit="updateRowList"
                  v-bind:refs="[row.rowKey,index]">
                    {{rowData}}</editable>
				<span class="printNewlines" v-else>{{rowData}}</span>
			</td>
		</tr>
		<tr v-if="displayList && displayList.length === 0"><td v-bind:colspan="headerInfoList.length" class="ta-center">No Results Found</td></tr>
	</tbody>
	</table>
</div>
</template>

<script>
export default {
			props: {  //These will be passed in from the parent page
				headerInfoList: Array,
				rowInfoList: Array,
				pageSize: { type: Number, default:100 },
				exportExcelName: String,
				rowHighlighting: Function,
				rowOnClick: Function
			},
			data:function(){ //These are local to the component
				return {
					firstRec:0,
					lastRec: this.pageSize -1,
					sortIndex:undefined
				}
			},
			created: function() {
				if(this.rowInfoList.length > 0 && this.headerInfoList.length > 0)
					this.defaultSort();
			},
			computed: { //These get recomputed whenever data changes
				displayList: function() {
					if(this.rowInfoList)
						return this.rowInfoList.slice(this.firstRec,this.lastRec);
					return [];
				}
			},
			watch:{ //This fires every time the rowInfoList changes, to wipe out the pagination
				rowInfoList: function (data) {
					this.firstRec = 0;
					this.lastRec = Math.min(this.rowInfoList.length, this.pageSize -1);
          for(var i=0; i < this.rowInfoList.length; i++){
            this.rowInfoList[i].rowKey = i;
          }
					if(this.rowInfoList.length > 0 && this.headerInfoList.length > 0)
						this.defaultSort();
				}
			},
			methods: {
				sort : function(columnIndex){
					this.sortIndex = columnIndex;
					var column = this.headerInfoList[columnIndex];

					if(column.sortable){
						if(column.sortOrder==='A'){
							column.sortOrder = 'D';
							this.rowInfoList.reverse();
						}
						else{
							column.sortOrder = 'A';
							this.rowInfoList.sort(column.sortType === "integer" ? integerListSort(columnIndex):alphaListSort(columnIndex));
						}
						this.headerInfoList.forEach(function(element){
							if(element!==column)
								element.sortOrder='';
						})
						this.displayList = this.rowInfoList.slice(this.firstRec,this.lastRec);
					}
				},
				reSort : function(){
					if (this.sortIndex !== undefined){
						this.sort(this.sortIndex);
						this.sort(this.sortIndex);
					}
				},
				defaultSort : function(){
					if(this.sortIndex == undefined){
						for(var i=0;i<this.headerInfoList.length;i++){
							if(this.headerInfoList[i].defaultSort){
								this.sort(i);
							}
						}
					}
				},
				nextPage : function (){
					this.firstRec = this.lastRec + 1;
					this.lastRec = Math.min(this.lastRec + parseInt(this.pageSize),this.rowInfoList.length-1);
					this.displayList = this.rowInfoList.slice(this.firstRec,this.lastRec);
				},

				prevPage : function(){
					var prevFirstRec = this.firstRec;
					this.lastRec = this.firstRec -1;
					this.firstRec = this.firstRec - this.pageSize;
					this.displayList = this.rowInfoList.slice(this.firstRec,this.lastRec);
				},
        updateRowList(newVal, refs){
          var row = this.rowInfoList[refs[0]];
          this.headerInfoList[refs[1]].editFunction(row, newVal);
        },
				exportToExcel : function(){
					var exportList = [];
					exportList.push(this.headerInfoList.map(function(header){
						return header.value;
					}));
					Array.prototype.push.apply(exportList,this.rowInfoList);
					JSONToCSVConvertor(exportList,this.exportExcelName);
				}
			}
}

function alphaListSort (columnIndex){
	return function (element1, element2){
		if(element1[columnIndex]<element2[columnIndex])
			return -1;
		if( element1[columnIndex]>element2[columnIndex])
			return 1;
		return 0;
	}
}

function integerListSort (columnIndex){
	return function (element1, element2){
		return element1[columnIndex]-element2[columnIndex];
	}
}

function JSONToCSVConvertor(JSONData, reportTitle) {
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
    var CSV = '';
    for (var i = 0; i < arrData.length; i++) {
        var row = "";

        for (var index in arrData[i]) {
            row += '"' + arrData[i][index] + '",';
        }

        row.slice(0, row.length - 1);
        CSV += row + '\r\n';
    }

    //this will remove the blank-spaces from the title and replace it with an underscore
    var fileName = reportTitle.replace(/ /g,"_");

    if (navigator.msSaveBlob) { // IE10
        navigator.msSaveBlob(new Blob([CSV], {type: 'text/csv'}), fileName+".csv");
    }
    else {
    	//Initialize file format you want csv or xls
    	var uri = 'data:text/csv;charset=utf-8,' + escape(CSV);
    	var link = document.createElement("a");
    	link.href = uri;
    	link.style = "visibility:hidden";
    	link.download = fileName + ".csv";

    	document.body.appendChild(link);
    	link.click();
    	document.body.removeChild(link);
    }
  }
</script>

<style>
#table-sortable td {
  position: relative;
}
.sortable-table {
	margin-top:20px;
}
.sortable-table .fa-angle-double-right, .sortable-table .fa-angle-double-left {
	margin-top: 10px;
	padding-left: 10px;
	padding-right: 10px;
	vertical-align: middle; height: 28px;
}
#table-sortable>thead>tr>th.columnHeader {
	border-top:solid #ddd;
	border-bottom:solid #ddd;
}

.sortDefault, .sortAsc, .sortDesc {
	cursor:pointer;
}
.sortDefault:hover {
	border-top: solid #b3b3b3 !important;
}

.sortDefault:hover, .sortDesc:hover {
	background-color:#ddd;
	border-bottom: solid #82a8c9 !important;
}

.sortAsc:hover {
	background-color:#ddd;
	border-top: solid #82a8c9 !important;
}

.sortAsc{
	border-bottom: thick solid #337ab7 !important;

}

.sortDesc{
	border-top: thick solid #337ab7 !important;
}

.printNewlines{
	white-space: pre-wrap;
}

</style>
