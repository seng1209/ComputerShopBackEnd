import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Table from "react-bootstrap/Table";
import Stack from "react-bootstrap/Stack";

const UpdateSale = () => {
  const { uuid } = useParams();
  const [disable, setDisable] = useState(false);
  const [salesProductsUuid, setSalesProductsUuid] = useState();
  const [productName, setProductName] = useState();
  const [sale, setSale] = useState({
    customerPhone: "",
    productUuid: "",
  });

  const { customerPhone, staffUuid } = sale;
  const [saleDetailResponse, setSaleDetailResponse] = useState({
    saleUuid: "",
    productUuid: "",
    saleQuantity: "",
    saleUnitPrice: "",
    discount: "",
  });
  const { saleUuid, productUuid, saleQuantity, saleUnitPrice, discount } =
    saleDetailResponse;

  const [saleDetails, setSaleDetail] = useState([]);

  const [staffs, setStaffs] = useState([]);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    laodStaffs();
    loadProducts();
    findBySaleUuid();
  }, []);

  const laodStaffs = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/staffs`);
      setStaffs(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  const loadProducts = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/products`);
      setProducts(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  const findBySaleUuid = async () => {
    try {
      const saleResult = await axios.get(
        `http://localhost:8080/api/v1/sales/${uuid}`
      );
      setSale({
        ...sale,
        customerPhone: saleResult.data.customer.phone,
        staffUuid: saleResult.data.staff.uuid,
      });
      const salesProductsResult = await axios.get(
        `http://localhost:8080/api/v1/sales_products/sale/${uuid}`
      );
      setSaleDetail(salesProductsResult.data);
    } catch (err) {
      console.log(err);
    }
  };

  const onMasterInputChange = (e) => {
    setSale({ ...sale, [e.target.name]: e.target.value });
  };

  const onSubmitMasterForm = async () => {
    try {
      await axios.patch(`http://localhost:8080/api/v1/sales/${uuid}`, sale);
    } catch (err) {
      console.log(err);
    }
    setDisable(true);
  };

  const onDetailInputChange = (e) => {
    setSaleDetailResponse({
      ...saleDetailResponse,
      [e.target.name]: e.target.value,
    });
  };

  const onSubmitDetail = async () => {
    console.log(salesProductsUuid);
    // console.log(saleDetailResponse);
    const saleDetailDto = { ...saleDetailResponse, saleUuid: uuid };
    console.log(saleDetailDto);
    try {
      await axios.patch(
        `http://localhost:8080/api/v1/sales_products/${salesProductsUuid}`,
        saleDetailDto
      );

      findBySaleUuid();
    } catch (err) {
      console.log(err);
    }
  };

  const updateSalesProducts = async (uuid) => {
    setSalesProductsUuid(uuid);
    try {
      const result = await axios.get(
        `http://localhost:8080/api/v1/sales_products/${uuid}`
      );
      setSaleDetailResponse({
        ...saleDetailResponse,
        productUuid: result.data.product.uuid,
        saleUnitPrice: result.data.saleUnitPrice,
        saleQuantity: result.data.saleQuantity,
        discount: result.data.discount,
      });
      setProductName(result.data.product.name);
    } catch (err) {
      console.log(err);
    }
  };
  return (
    <div>
      <Stack gap={3}>
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
                        value={customerPhone}
                        placeholder="customer phone"
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Label>Staff UUID</Form.Label>
                    <Form.Select
                      name="staffUuid"
                      value={staffUuid}
                      onChange={(e) => onMasterInputChange(e)}
                      aria-label="Default select example"
                    >
                      <option disabled selected>
                        Staffs Name
                      </option>
                      {staffs.map((staff, index) => {
                        return (
                          <option key={index} value={staff.uuid}>
                            {staff.name}
                          </option>
                        );
                      })}
                    </Form.Select>
                  </Col>
                </Row>
                <Row>
                  <Col md={3}>
                    <Button
                      variant="primary"
                      disabled={disable}
                      onClick={onSubmitMasterForm}
                    >
                      Update Sale
                    </Button>
                  </Col>
                </Row>
              </Form>
            </Col>
            <Col>
              <Form>
                <Row>
                  <Col>
                    <Form.Group
                      className="mb-3"
                      controlId="exampleForm.ControlInput1"
                    >
                      <Form.Label>Product UUID</Form.Label>
                      <Form.Control
                        disabled
                        type="text"
                        name="productUuid"
                        value={productName}
                        onChange={(e) => onDetailInputChange(e)}
                        placeholder="product uuid"
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group
                      className="mb-3"
                      controlId="exampleForm.ControlInput1"
                    >
                      <Form.Label>Sale Quantity</Form.Label>
                      <Form.Control
                        type="number"
                        name="saleQuantity"
                        onChange={(e) => onDetailInputChange(e)}
                        value={saleQuantity}
                        placeholder="sale quantity"
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
                      <Form.Label>Sale Unit Price</Form.Label>
                      <Form.Control
                        disabled
                        type="number"
                        name="saleUnitPrice"
                        onChange={(e) => onDetailInputChange(e)}
                        value={saleUnitPrice}
                        placeholder="sale unit price"
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group
                      className="mb-3"
                      controlId="exampleForm.ControlInput1"
                    >
                      <Form.Label>Discount</Form.Label>
                      <Form.Control
                        type="number"
                        name="discount"
                        onChange={(e) => onDetailInputChange(e)}
                        value={discount}
                        placeholder="discount"
                      />
                    </Form.Group>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <Button variant="warning" onClick={onSubmitDetail}>
                      Update Sale Detail
                    </Button>
                  </Col>
                  <Col>
                    <Button href="/sale" variant="danger">
                      Exit
                    </Button>
                  </Col>
                </Row>
              </Form>
            </Col>
          </Row>
        </Container>

        <Container>
          <Row>
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
                  {saleDetails?.map((saleDetail, index) => {
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
                            onClick={() => updateSalesProducts(saleDetail.uuid)}
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
          </Row>
        </Container>
      </Stack>
    </div>
  );
};

export default UpdateSale;
