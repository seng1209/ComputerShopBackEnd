import { useState, useEffect } from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import Container from "react-bootstrap/Container";
import Stack from "react-bootstrap/Stack";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Table from "react-bootstrap/Table";

function CreateNewSale() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [success, setSuccess] = useState();

  const [sale, setSale] = useState({
    customerPhone: "",
    staffUuid: "",
  });

  const [saleDetail, setSaleDetail] = useState({
    saleUuid: "",
    productUuid: "",
    saleQuantity: "",
    saleUnitPrice: "",
    discount: "",
  });

  const [saleDetailResponse, setSaleDetailResponse] = useState([]);

  const [createSale, setCreateSale] = useState([]);

  const [staffs, setStaffs] = useState([]);

  const [products, setProducts] = useState([]);
  const [product, setProduct] = useState({
    productUuid: "",
    saleUnitPrice: "",
  });

  const { productUuid, saleUnitPrice } = product;

  const [disable, setDisable] = useState(false);

  useEffect(() => {
    laodStaffs();
    loadProducts();
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

  const loadSalesProducts = async () => {
    try {
      const result = await axios.get(
        `http://localhost:8080/api/v1/sales_products/sale/${createSale.uuid}`
      );
      setSaleDetailResponse(result.data);
      console.log(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  const onSelectChange = async (e) => {
    try {
      const result = await axios.get(
        `http://localhost:8080/api/v1/products/${e.target.value}`
      );
      setProduct(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  const onMasterInputChange = (e) => {
    setSale({ ...sale, [e.target.name]: e.target.value });
  };

  const createNewSale = async () => {
    try {
      const result = await axios.post(
        `http://localhost:8080/api/v1/sales`,
        sale
      );
      setCreateSale(result.data);
    } catch (err) {
      console.log(err);
    }
    setDisable(true);
  };

  const onDetailInputChange = (e) => {
    setSaleDetail({ ...saleDetail, [e.target.name]: e.target.value });
  };

  const onSubmit = async () => {
    const saleDetailRequest = {
      ...saleDetail,
      saleUuid: createSale.uuid,
      productUuid: product.uuid,
      saleUnitPrice: product.saleUnitPrice,
    };
    try {
      const result = await axios.post(
        `http://localhost:8080/api/v1/sales_products`,
        saleDetailRequest
      );
      await axios.put(
        `http://localhost:8080/api/v1/sales_products/sale/${createSale.uuid}`
      );
      loadSalesProducts();
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
      <div>
        <Stack gap={3}>
          <Container>
            <Row>
              <Col>
                <Form>
                  <Container>
                    <Row>
                      <Col>
                        <Form.Group
                          className="mb-3"
                          controlId="exampleForm.ControlInput1"
                        >
                          <Form.Label>Customer Phone</Form.Label>
                          <Form.Control
                            type="text"
                            placeholder="customer phone"
                            name="customerPhone"
                            onChange={(e) => onMasterInputChange(e)}
                            autoFocus
                          />
                        </Form.Group>
                      </Col>
                      <Col>
                        <Form.Label>Staff UUID</Form.Label>
                        <Form.Select
                          name="staffUuid"
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
                    </Row>{" "}
                    <Row>
                      <Col md={4}>
                        <Button
                          variant="primary"
                          onClick={createNewSale}
                          disabled={disable}
                        >
                          Add New Sale
                        </Button>
                      </Col>
                    </Row>
                  </Container>
                </Form>
              </Col>
              <Col>
                <Form>
                  <Container>
                    <Row>
                      <Col md={6}>
                        <Form.Label>Products UUID</Form.Label>
                        <Form.Select
                          name="productUuid"
                          onChange={(e) => onSelectChange(e)}
                          aria-label="Default select example"
                        >
                          <option disabled selected>
                            Products Name
                          </option>
                          {products.map((product, index) => {
                            return (
                              <option key={index} value={product.uuid}>
                                {product.name}
                              </option>
                            );
                          })}
                        </Form.Select>
                      </Col>
                      <Col md={6}>
                        <Form.Group
                          className="mb-3"
                          controlId="exampleForm.ControlInput1"
                        >
                          <Form.Label>Sale Quantity</Form.Label>
                          <Form.Control
                            type="text"
                            placeholder="sale quantity"
                            name="saleQuantity"
                            onChange={(e) => onDetailInputChange(e)}
                            autoFocus
                          />
                        </Form.Group>
                      </Col>
                      <Col md={6}>
                        <Form.Group
                          className="mb-3"
                          controlId="exampleForm.ControlInput1"
                        >
                          <Form.Label>Sale Unit Price</Form.Label>
                          <Form.Control
                            type="number"
                            placeholder="sale unit price"
                            name="saleUnitPrice"
                            value={saleUnitPrice}
                            onChange={(e) => onDetailInputChange(e)}
                            disabled
                            autoFocus
                          />
                        </Form.Group>
                      </Col>
                      <Col md={6}>
                        <Form.Group
                          className="mb-3"
                          controlId="exampleForm.ControlInput1"
                        >
                          <Form.Label>Discount</Form.Label>
                          <Form.Control
                            type="number"
                            placeholder="discount"
                            name="discount"
                            onChange={(e) => onDetailInputChange(e)}
                            autoFocus
                          />
                        </Form.Group>
                      </Col>
                    </Row>
                    <Row>
                      <Col>
                        <Button variant="primary" onClick={onSubmit}>
                          Add New SaleDetail
                        </Button>
                      </Col>
                      <Col>
                        <Button href="/sale" variant="danger">
                          Exit
                        </Button>
                      </Col>
                    </Row>
                  </Container>
                </Form>
              </Col>
            </Row>
          </Container>

          <Container fluid>
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
                    </tr>
                  </thead>
                  <tbody>
                    {saleDetailResponse?.map((saleDetails, index) => {
                      return (
                        <tr key={index}>
                          <td>{index + 1}</td>
                          <td>{saleDetails.sale.uuid}</td>
                          <td>{saleDetails.product.name}</td>
                          <td>{saleDetails.saleQuantity}</td>
                          <td>{saleDetails.saleUnitPrice}</td>
                          <td>{saleDetails.discount}</td>
                          <td>{saleDetails.amount}</td>
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
      {/* <>
        <>
          <Button variant="primary" onClick={handleShow}>
            New
          </Button>

          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Add New Sale</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Stack gap={3}>
                <Form>
                  <Container>
                    <Row>
                      <Col md={6}>
                        <Form.Group
                          className="mb-3"
                          controlId="exampleForm.ControlInput1"
                        >
                          <Form.Label>Customer Phone</Form.Label>
                          <Form.Control
                            type="text"
                            placeholder="customer phone"
                            name="customerPhone"
                            onChange={(e) => onMasterInputChange(e)}
                            autoFocus
                          />
                        </Form.Group>
                      </Col>
                      <Col md={6}>
                        <Form.Label>Staff UUID</Form.Label>
                        <Form.Select
                          name="staffUuid"
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
                      <Col>
                        <Button
                          variant="primary"
                          onClick={createNewSale}
                          disabled={disable}
                        >
                          Add New Sale
                        </Button>
                      </Col>
                    </Row>
                  </Container>
                </Form>
                <Form>
                  <Container>
                    <Row>
                      <Col md={6}>
                        <Form.Label>Products UUID</Form.Label>
                        <Form.Select
                          name="productUuid"
                          onChange={(e) => onSelectChange(e)}
                          aria-label="Default select example"
                        >
                          <option disabled selected>
                            Products Name
                          </option>
                          {products.map((product, index) => {
                            return (
                              <option key={index} value={product.uuid}>
                                {product.name}
                              </option>
                            );
                          })}
                        </Form.Select>
                      </Col>
                      <Col md={6}>
                        <Form.Group
                          className="mb-3"
                          controlId="exampleForm.ControlInput1"
                        >
                          <Form.Label>Sale Quantity</Form.Label>
                          <Form.Control
                            type="text"
                            placeholder="sale quantity"
                            name="saleQuantity"
                            onChange={(e) => onDetailInputChange(e)}
                            autoFocus
                          />
                        </Form.Group>
                      </Col>
                      <Col md={6}>
                        <Form.Group
                          className="mb-3"
                          controlId="exampleForm.ControlInput1"
                        >
                          <Form.Label>Sale Unit Price</Form.Label>
                          <Form.Control
                            type="number"
                            placeholder="sale unit price"
                            name="saleUnitPrice"
                            value={saleUnitPrice}
                            onChange={(e) => onDetailInputChange(e)}
                            disabled
                            autoFocus
                          />
                        </Form.Group>
                      </Col>
                      <Col md={6}>
                        <Form.Group
                          className="mb-3"
                          controlId="exampleForm.ControlInput1"
                        >
                          <Form.Label>Discount</Form.Label>
                          <Form.Control
                            type="number"
                            placeholder="discount"
                            name="discount"
                            onChange={(e) => onDetailInputChange(e)}
                            autoFocus
                          />
                        </Form.Group>
                      </Col>
                      <Col>
                        <Button variant="primary" onClick={onSubmit}>
                          Add New SaleDetail
                        </Button>
                      </Col>
                    </Row>
                  </Container>
                </Form>
                <Container fluid>
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
                          </tr>
                        </thead>
                        <tbody>
                          {saleDetailResponse.map((saleDetail, index) => {
                            return (
                              <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{saleDetail.sale.uuid}</td>
                                <td>{saleDetail.product.name}</td>
                                <td>{saleDetail.saleQuantity}</td>
                                <td>{saleDetail.saleUnitPrice}</td>
                                <td>{saleDetail.discount}</td>
                                <td>{saleDetail.amount}</td>
                              </tr>
                            );
                          })}
                        </tbody>
                      </Table>
                    </Col>
                  </Row>
                </Container>
              </Stack>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
            </Modal.Footer>
          </Modal>
        </>
      </> */}
    </>
  );
}

export default CreateNewSale;
