import React, { useState, useEffect } from "react";
import axios from "axios";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Table from "react-bootstrap/Table";

const CreateInvoice = () => {
  const [disable, setDisable] = useState(false);

  const [customers, setCustomers] = useState([]);

  const [paymentUuid, setPaymentUuid] = useState();

  const [saleUuid, setSaleUuid] = useState();

  const [payment, setPayment] = useState([]);

  const { paidAmount } = payment;

  const [sale, setSale] = useState([]);

  const { totalAmount } = sale;

  const [oweAmount, setOweAmount] = useState(0);

  const [invoiceRequest, setInvoiceRequest] = useState({
    oweAmount: "",
    paidAmount: "",
    totalAmount: "",
    customerPhone: "",
  });

  const [invoiceResponse, setInvoiceResponse] = useState({
    invoiceDate: "",
    totalAmount: "",
    paidAmount: "",
    oweAmount: "",
    customer: "",
  });

  const [invoicesProductsRequest, setInvoicesProductsRequest] = useState({
    invoiceUuid: "",
    productUuid: "",
    saleQuantity: "",
    saleUnitPrice: "",
  });

  const [salesProductsResponse, setSalesProductsResponse] = useState([]);

  useEffect(() => {
    loadCustomers();
  }, []);

  const loadCustomers = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/customers`);
      setCustomers(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  const findByPaymentUuid = async (e) => {
    try {
      const result = await axios.get(
        `http://localhost:8080/api/v1/payments/${e.target.value}`
      );
      setPayment(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  const findBySaleUuid = async (e) => {
    try {
      const result = await axios.get(
        `http://localhost:8080/api/v1/sales/${e.target.value}`
      );
      setSale(result.data);
      const salesProductsResult = await axios.get(
        `http://localhost:8080/api/v1/sales_products/sale/${saleUuid}`
      );
      setSalesProductsResponse(salesProductsResult.data);
      console.log(salesProductsResult.data);
    } catch (err) {
      console.log(err);
    }
  };

  const findOweAmount = () => {
    setOweAmount(totalAmount - paidAmount);
  };

  // Master Form
  const onMasterInputChange = (e) => {
    setInvoiceRequest({ ...invoiceRequest, [e.target.name]: e.target.value });
  };

  const onSubmitMasterForm = async () => {
    console.log(invoiceRequest);
    const invoiceRequestDto = {
      ...invoiceRequest,
      oweAmount: oweAmount,
      paidAmount: paidAmount,
      totalAmount: totalAmount,
    };
    console.log(invoiceRequestDto);
    try {
      const result = await axios.post(
        `http://localhost:8080/api/v1/invoices`,
        invoiceRequestDto
      );
      setInvoiceResponse(result.data);
      console.log(result.data);
    } catch (err) {
      console.log(err);
    }
    setDisable(true);
  };

  // Detail
  const onDetailInputChange = (e) => {
    setInvoicesProductsRequest({
      ...invoicesProductsRequest,
      [e.target.name]: e.target.value,
    });
  };

  const onSubmitDetailForm = async () => {
    console.log(invoicesProductsRequest);
    alert("Add successully");
  };
  return (
    <div>
      <Container>
        <Row>
          <Col>
            <Form>
              <Row>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Customer Phone</Form.Label>
                    <Form.Control
                      type="text"
                      name="customerPhone"
                      onChange={(e) => onMasterInputChange(e)}
                      placeholder="customer phone"
                    />
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Owe Amount</Form.Label>
                    <Form.Control
                      type="number"
                      name="oweAmount"
                      value={oweAmount}
                      onChange={(e) => onMasterInputChange(e)}
                      placeholder="owe amount"
                    />
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Payment UUID</Form.Label>
                    <Form.Control
                      type="text"
                      name="paymentUuid"
                      onChange={(e) => findByPaymentUuid(e)}
                      placeholder="payment uuid"
                    />
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Sale UUID</Form.Label>
                    <Form.Control
                      type="text"
                      name="saleUuif"
                      onChange={(e) => findBySaleUuid(e)}
                      placeholder="sale uuid"
                    />
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Paid Amount</Form.Label>
                    <Form.Control
                      disabled
                      type="number"
                      name="paidAmount"
                      value={paidAmount}
                      // onChange={(e) => onMasterInputChange(e)}
                      placeholder="paid amount"
                    />
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Group
                    className="mb-3"
                    controlId="exampleForm.ControlInput1"
                  >
                    <Form.Label>Total Amount</Form.Label>
                    <Form.Control
                      disabled
                      type="number"
                      name="totalAmount"
                      value={totalAmount}
                      // onChange={(e) => onMasterInputChange(e)}
                      placeholder="total amount"
                    />
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col md={4}>
                  <Button
                    disabled={disable}
                    variant="primary"
                    onClick={onSubmitMasterForm}
                  >
                    Add New Invoice
                  </Button>
                </Col>
                <Col>
                  <Button variant="primary" onClick={findOweAmount}>
                    Check
                  </Button>
                </Col>
                <Col>
                  <Button variant="danger" href="/invoice">
                    Exit
                  </Button>
                </Col>
              </Row>
            </Form>
          </Col>
          {/* <Col>
            <Form>
              <Form.Label>Customer Phone</Form.Label>
              <Form.Select aria-label="Default select example">
                <option>Open this select menu</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
              </Form.Select>
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlInput1"
              >
                <Form.Label>Owe Amount</Form.Label>
                <Form.Control type="number" placeholder="owe amount" />
              </Form.Group>
            </Form>
          </Col> */}
        </Row>
        {/* <Row>
          <Col>
            <Table striped bordered hover>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Sale UUID</th>
                  <th>Product Name</th>
                  <th>Sale Quantity</th>
                  <th>Sale Unit Price</th>
                  <th>Discount</th>
                  <th>Amount</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {salesProductsResponse?.map((saleDetail, index) => {
                  return (
                    <tr key={index}>
                      <td>{index + 1}</td>
                      <td>{saleDetail.sale.uuid}</td>
                      <td>{saleDetail.product.name}</td>
                      <td>{saleDetail.saleQuantity}</td>
                      <td>{saleDetail.saleUnitPrice}</td>
                      <td>{saleDetail.discount}</td>
                      <td>{saleDetail.amount}</td>
                      <td>
                        <Button
                          variant="warning"
                          // onClick={() => updateSalesProducts(saleDetail.uuid)}
                        >
                          Edit
                        </Button>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </Table>
          </Col>
        </Row> */}
      </Container>
    </div>
  );
};

export default CreateInvoice;

// CreateInvoice
