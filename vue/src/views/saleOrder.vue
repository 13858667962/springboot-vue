<template>
  <div style="width: calc(100vh - 50px)">
    <div>
      <el-table
          ref="multipleTable"
          :data="tableData"
          style="width:auto"
          stripe
          height="40vh"
          @selection-change="handleSelectionChange"
      >
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            sortable
            prop="saleOrderCode"
            label="销售订单号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="saleOrderType"
            label="销售订单类型"
            width="180">
        </el-table-column>
        <el-table-column
            prop="signDate"
            label="签订日期">
        </el-table-column>
        <el-table-column
            prop="clientId"
            label="客户编码">
        </el-table-column>
        <el-table-column
            prop="soMaker"
            label="编制人">
        </el-table-column>
        <el-table-column
            prop="soAuditor"
            label="审核人">
        </el-table-column>
        <el-table-column
            prop="soApprover"
            label="批准人">
        </el-table-column>
      </el-table>
    </div>
    <div style="height: 7vh;margin-top: 10px">
      <el-button style="margin-right: 10px;margin-left: 10px" type="primary" @click="editFormVisible=true">编辑
      </el-button>
      <el-button style="margin-right: 10px" type="primary" @click="dialogFormVisible=true">新增</el-button>
      <el-popconfirm
          confirm-button-text='好的'
          cancel-button-text='不用了'
          icon="el-icon-info"
          icon-color="red"
          title="删除选中数据吗？"
          @confirm="deleteSelect"
      >
        <el-button slot="reference" type="primary">删除</el-button>
      </el-popconfirm>
      <el-button style="float: right;margin-right: 10px" type="primary" @click="selectById">查找</el-button>
      <el-input style="float: right;width: 300px;margin-right: 10px" v-model="input" placeholder="请输入内容"></el-input>
    </div>
    <div>
      <el-table
          :data="tableData2"
          style="width: 100%"
          stripe
          height=45vh>
        <el-table-column
            sortable
            prop="saleOrderCode"
            label="销售订单号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="productId"
            label="产品编号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="deliveryDate"
            label="交货日期">
        </el-table-column>
        <el-table-column
            prop="soQuantity"
            label="订购数量">
        </el-table-column>
        <el-table-column
            prop="price"
            label="价格">
        </el-table-column>
        <el-table-column
            prop="address"
            label="地址">
        </el-table-column>
      </el-table>
    </div>


    <el-dialog v-model="dialogFormVisible" :visible.sync="dialogFormVisible" title="新增" width="60%" append-to-body>
      <el-form :model="form" :rules="rules" ref="form" label-width="120px" style="width: 100%">
        <el-row>
          <el-col :span="8">
            <el-form-item label="销售订单号" prop="saleOrderCode">
              <el-input v-model="form.saleOrderCode"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="销售订单类型" prop="saleOrderType">
              <el-input v-model="form.saleOrderType"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="签订日期" prop="signDate">
              <el-date-picker
                  v-model="form.signDate"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交货日期" prop="deliveryDate">
              <el-date-picker
                  v-model="form.deliveryDate"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="编制人" prop="soMaker">
              <el-input v-model="form.soMaker"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="审核人" prop="soAuditor">
              <el-input v-model="form.soAuditor"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="批准人" prop="soApprover">
              <el-input v-model="form.soApprover"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品编号" prop="productId">
              <el-input v-model="form.productId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">

            <el-form-item label="客户编码" prop="clientId">
              <el-input v-model="form.clientId"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="订购数量" prop="soQuantity">
              <el-input v-model="form.soQuantity"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="价格" prop="price">
              <el-input v-model="form.price"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="描述" prop="soDescription">
          <el-input v-model="form.soDescription" type="textarea"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="add">确 定</el-button>
  </span>
    </el-dialog>

    <el-dialog v-model="editFormVisible" :visible.sync="editFormVisible" title="编辑" width="60%" append-to-body>
      <el-form :model="edit" :rules="editRules" ref="edit" label-width="120px" style="width: 100%">
        <el-row>
          <el-col :span="8">
            <el-form-item label="销售订单号" prop="saleOrderCode">
              <el-input v-model="edit.saleOrderCode" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="销售订单类型" prop="saleOrderType">
              <el-input v-model="edit.saleOrderType"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="签订日期" prop="signDate">
              <el-date-picker
                  v-model="edit.signDate"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交货日期" prop="deliveryDate">
              <el-date-picker
                  v-model="edit.deliveryDate"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="编制人" prop="soMaker">
              <el-input v-model="edit.soMaker"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="审核人" prop="soAuditor">
              <el-input v-model="edit.soAuditor"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="批准人" prop="soApprover">
              <el-input v-model="edit.soApprover"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品编号" prop="productId">
              <el-input v-model="edit.productId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">

            <el-form-item label="客户编码" prop="clientId">
              <el-input v-model="edit.clientId"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="订购数量" prop="soQuantity">
              <el-input v-model="edit.soQuantity"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="价格" prop="price">
              <el-input v-model="edit.price"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="描述" prop="soDescription">
          <el-input v-model="edit.soDescription" type="textarea"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="editFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="editData">确 定</el-button>
  </span>
    </el-dialog>

  </div>


</template>

<script>
import request from "../utils/request";

export default {
  name: "saleOrder",
  components: {},
  data() {
    return {
      input: '',
      dialogFormVisible: false,
      editFormVisible:false,
      tableData: [],
      tableData2: [],
      form: {
        saleOrderCode: '',
      },
      multipleSelection: [],
      ids: [],
      rules: {
        saleOrderCode: [
          {required: true, message: "订单号不能为空", trigger: "blur"},

        ],
      },
      edit:{
        saleOrderCode:'',
      },
      editRules:{
        saleOrderCode: [
          {required: true, message: "订单号不能为空", trigger: "blur"},

        ],
      }

    }
  },
  created() {
    this.load();
  },
  methods: {
    add() {
      console.log(this.form)
      request.post("/api/order/insert", this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "添加成功"
          })
          this.load()
          this.dialogFormVisible = false
        }else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
      })
    },
    load() {
      request.post("/api/order/query").then(res => {
        this.tableData = res.data;
      })
    },
    deleteSelect() {
      for (let i = 0; i < this.multipleSelection.length; i++) {
        this.ids.push(this.multipleSelection[i].saleOrderCode)
      }
      request.post("api/order/delete", this.ids).then(res => {
        this.load()
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.tableData2 = this.multipleSelection;
      if(this.multipleSelection.length===1)
        this.edit=this.multipleSelection[0];
    },
    selectById() {
      request.get("api/order/selectById/" + this.input).then(res => {
        if (res.code === '0') {
          this.tableData = res.data
          this.$message({
            type: "success",
            message: "查询成功"
          })
        } else {
          this.$message({
            type: 'error',
            message: res.msg
          })
        }
      })
    },
    editData(){
      console.log(this.multipleSelection.length)
      if(this.multipleSelection.length==1){
        console.log(this.edit)
        request.put("/api/order/editById",this.edit).then(res=>{
          if(res.code==='0'){
            this.$message({
              type:"success",
              message:"编辑成功"
            })
            this.editFormVisible=false;
            this.load()
          }
        })
      }
    }
  }

}

</script>

<style scoped>

</style>